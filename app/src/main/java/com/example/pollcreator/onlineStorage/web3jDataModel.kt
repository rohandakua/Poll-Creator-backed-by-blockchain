package com.example.pollcreator.onlineStorage

import android.util.Log
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.contract.PollCreator
import com.example.pollcreator.dataclass.Candidate
import com.example.pollcreator.dataclass.Event
import com.example.pollcreator.dataclass.Gender
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.dataclass.PollResultObj
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.repository.web3jRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
//import com.walletconnect.android.internal.common.model.WalletConnectUri
//import com.walletconnect.web3.wallet.client.Wallet

import kotlinx.coroutines.tasks.await
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.math.BigInteger
import java.util.Date

class web3jDataModel() : web3jRepository {
    var privateKey = allSingeltonObjects.privateKeyViewModelObject.privateKey.value.toString()
    val web3j: Web3j = Web3j.build(HttpService(allSingeltonObjects.sepoliaUrl))
    lateinit var credential : Credentials
    lateinit var contract: PollCreator
    init {
        CoroutineScope(Dispatchers.IO).launch {
            privateKey = allSingeltonObjects.privateKeyViewModelObject.privateKey.value.toString()
            Log.d("private key from web3j","${privateKey}")
            credential = Credentials.create(privateKey)
            createWeb3jObject()
        }
    }


    suspend fun createWeb3jObject() {
        CoroutineScope(Dispatchers.IO).launch {


            try {
                allSingeltonObjects.helperFunctions.isValidPrivateKey(privateKey)

                credential = Credentials.create(privateKey.toString())
                contract = async {
                    PollCreator.load(
                        allSingeltonObjects.contractAddress,
                        web3j,
                        credential,
                        org.web3j.tx.gas.DefaultGasProvider()
                    )
                }.await()
                if (contract.isValid) {
                    Log.d("contract", "contract is valid")
                } else {
                    Log.d("contract", "everything is fine")
                }
            } catch (e: Exception) {
                Log.d("error from web3j", e.toString())
            }
        }
    }


    override suspend fun createPoll(poll: Poll): Event {
        if (true) {//allSingeltonObjects.profileViewModel.isAdmin.value
            // creating the poll in web3j
            try {
                val receipt = contract.createPoll(
                    BigInteger(poll._pollId.toString()),
                    poll._agendaOfPoll,
                    BigInteger(poll._startTime.toString()),
                    BigInteger(poll._endTime.toString()),
                    BigInteger(poll._eligibleVoterAge.toString()),
                    BigInteger(allSingeltonObjects.profileViewModel.aadharNo.value)
                ).sendAsync()
                receipt.await()

                // adding this to users in firebase
                Log.d("no of poll","${poll._pollId.substring(12).toInt()}")
                allSingeltonObjects.referenceToUsers.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("noOfPollCreated").setValue(poll._pollId.substring(12).toInt())

                Log.d("receipt", receipt.toString())
                return Event.SUCCESS
            } catch (e: Exception) {
                Log.d("receipt", e.toString())
                return Event.FAILURE
            }

        } else {
            Log.d("createPoll", "user is not a admin")
            return Event.FAILURE
        }


    }

    // candidateIdInput is the index of the candidate in the list of candidate of the poll
    override suspend fun castVote(
        pollid: String,
        aadharNoOfCandidate: String,
        gender: String,
        aadharNoOfVoter: String
    ): Event {
        // adding the vote to blockchain
        Log.d("pollid",pollid)
        Log.d("aadharNoOfCandidate",aadharNoOfCandidate)
        try {
            val receipt = contract.castVote(
                BigInteger("${pollid}"),  //  find the index of the candidate in the list of candidate of the poll
                BigInteger("${aadharNoOfCandidate.toString()}"),
                BigInteger("${aadharNoOfVoter.toString()}")
            ).sendAsync()
            receipt.await()


            // adding the aadhar no of the Voter to the list of voter who have voted
            allSingeltonObjects.referenceToPolls.child("${pollid}").child("_listOfVoterWhoHaveVoted")
                .push().setValue(aadharNoOfVoter).await()
            // adding a vote to gender wise list
            if (gender == "male") {
                allSingeltonObjects.referenceToPolls.child("${pollid}").child("_noOfMaleVoter")
                    .setValue(
                        allSingeltonObjects.referenceToPolls.child("${pollid}")
                            .child("_noOfMaleVoter").get().await().value.toString().toLong() + 1
                    ).await()
            } else {
                allSingeltonObjects.referenceToPolls.child("${pollid}").child("_noOfFemaleVoter")
                    .setValue(
                        allSingeltonObjects.referenceToPolls.child("${pollid}")
                            .child("_noOfFemaleVoter").get().await().value.toString().toLong() + 1
                    ).await()
            }


            //adding the vote to the pollresultobject
            allSingeltonObjects.referenceToPolls.child("${pollid}").child("_listOfPollResult")
                .child("${aadharNoOfCandidate}").child("noOfVote")
                .setValue(
                    allSingeltonObjects.referenceToPolls.child("${pollid}").child("_listOfPollResult")
                        .child("${aadharNoOfCandidate}")
                        .child("noOfVote").get().await().value.toString().toLong() + 1
                ).await()


            Log.d("receipt", receipt.toString())
        } catch (e: Exception) {
            Log.d("castVote", e.toString())
            return Event.FAILURE
        }





        return Event.SUCCESS

    }

    override suspend fun getAllPollCreatedByAdmin(aadharNoOfAdmin: String): List<Poll> {
        var pollList: MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for (poll in snapShot.children) {
            val pollTemp = poll.getValue(Poll::class.java)
            if (pollTemp != null && pollTemp._pollCreatedBy==aadharNoOfAdmin) {
                pollList.add(pollTemp)
            }

        }
        pollList.sortByDescending { it._startTime }   // giving the value in descending order by startTime
        return pollList

    }suspend fun getPrevPollCreatedByAdmin(aadharNoOfAdmin: String): List<Poll> {
        var pollList: MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for (poll in snapShot.children) {
            val pollTemp = poll.getValue(Poll::class.java)
            if (pollTemp != null && pollTemp._pollCreatedBy==aadharNoOfAdmin &&
                allSingeltonObjects.helperFunctions.convertToUnixTimestamp(Date()) >pollTemp._endTime) {
                pollList.add(pollTemp)
            }

        }
        pollList.sortByDescending { it._startTime }   // giving the value in descending order by startTime
        println(pollList)
        return pollList

    }
    suspend fun getPollThatAdminCanPartiticipate(aadharNoOfAdmin:String): List<Poll> {
        var pollList: MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for (poll in snapShot.children) {
            val pollTemp = poll.getValue(Poll::class.java)
            if (pollTemp != null &&
                pollTemp._eligibleVoterAge<=allSingeltonObjects.signInViewModel.age.value.toInt() &&
                allSingeltonObjects.helperFunctions.convertToUnixTimestamp(Date())<=pollTemp._startTime) {
                pollList.add(pollTemp)
            }


        }
        pollList.sortByDescending { it._startTime }   // giving the value in descending order by startTime
        println(pollList)
        return pollList

    }

    override suspend fun getAllUpcomingPoll(age: Int): List<Poll> {

        // adding the poll from firebase realtime database to the list of poll
        val pollList: MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for (poll in snapShot.children) {
            val pollTemp = poll.getValue(Poll::class.java)
            pollTemp?.let {
                if (pollTemp._eligibleVoterAge <= age && allSingeltonObjects.helperFunctions.convertToUnixTimestamp(Date())<=pollTemp._endTime
                ) {
                    pollList.add(pollTemp)
                }
            }
        }
        pollList.sortBy { it._startTime }   // giving the value in ascending order by startTime

        return pollList
    }

    //allSingeltonObjects.referenceToPolls.child("${pollid}").child("_listOfVoterWhoHaveVoted")
//            .push().setValue(aadharNoOfVoter).await()
    override suspend fun getPreviousPoll(aadharNoOfVoter: String): List<Poll> {
        var pollList: MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for (poll in snapShot.children) {
            val pollTemp = poll.getValue(Poll::class.java)
            if (pollTemp != null && pollTemp._listOfVoterWhoHaveVoted.contains(aadharNoOfVoter)) {
                pollList.add(pollTemp)
            }

        }
        pollList.sortByDescending { it._startTime }   // giving the value in descending order by startTime
        return pollList
    }

    override suspend fun becomeCandidateOfPoll(candidate: Candidate): Event {
        /*pollid: String,
        aadharNoOfAdmin: Long,
        age: Int,
        agendaOfPoll: String
         */
        try {
            val receipt = contract.createCandidate(
                BigInteger("${candidate._aadharNo.toString()}"),
                BigInteger("${candidate._pollId.toString()}"),
                candidate._agenda.toString(),
                BigInteger("${candidate._age.toString()}")
            ).sendAsync()
            receipt.await()
            allSingeltonObjects.referenceToPolls.child("${candidate._pollId}").child("_listOfCandidate")
                .child("${candidate._aadharNo}").setValue(PollResultObj(candidate,0L)).await()
            // inserting in the list of candidate of the poll
            // poll id -> { , , , , _listOfPoll -> { candidate_aadharNo -> candidate } }

            // adding the pollId in alladmin in firebase
            if(candidate._gender==Gender.MALE){
                allSingeltonObjects.referenceToPolls.child(candidate._pollId).child("_noOfMaleVoter").setValue(allSingeltonObjects.referenceToPolls.child(candidate._pollId).child("_noOfMaleVoter").get().await().value.toString().toLong()+1).await()
            }else{
                allSingeltonObjects.referenceToPolls.child(candidate._pollId).child("_noOfFemaleVoter").setValue(allSingeltonObjects.referenceToPolls.child(candidate._pollId).child("_noOfFemaleVoter").get().await().value.toString().toLong()+1).await()

            }

            allSingeltonObjects.referenceToAllAdmins.child("${allSingeltonObjects.profileViewModel.aadharNo.value}").child("_pollsCreated").push().setValue(candidate._pollId).await()
            Log.d("receipt", receipt.toString())



        } catch (e: Exception) {
            Log.d("receipt", e.toString())
            return Event.FAILURE
        }

        return Event.SUCCESS


    }

    override suspend fun becomeVoter(userOrAdmin: UserOrAdmin): Event {
        return try {
            // Call the contract's createVoter function
            val receipt = contract.createVoter(
                BigInteger(userOrAdmin._aadharNo.toString()),
                BigInteger(userOrAdmin._age.toString())
            ).sendAsync().await()  // Wait for the transaction to be completed

            // Check the transaction status
            if (receipt.status == "0x1") {
                // Transaction was successful

                // Adding the userOrAdmin to Firebase Realtime Database in allvoters
                allSingeltonObjects.referenceToAllVoters
                    .child("${userOrAdmin._aadharNo}")
                    .setValue(userOrAdmin).await()

                Log.d("receipt", "Transaction successful: $receipt")
                Event.SUCCESS
            } else {
                // Transaction failed
                Log.d("receipt", "Transaction failed: $receipt")
                Event.FAILURE
            }

        } catch (e: Exception) {
            Log.e("receipt", "Error: ${e.message}")
            Event.FAILURE
        }

//        //aadharNo: Long, age: Int
//        try {
//            val receipt = contract.createVoter(
//                BigInteger("${userOrAdmin._aadharNo.toString()}"),
//                BigInteger("${userOrAdmin._age.toString()}")
//            ).sendAsync()
//            receipt.await()
//
//            // adding the useroradmin to the firebase realtime database in allvoters
//            allSingeltonObjects.referenceToAllVoters.child("${userOrAdmin._aadharNo}")
//                .setValue(userOrAdmin).await()
//            // aadharNo->UserOrAdmin
//            Log.d("receipt", receipt.toString())
//
//        } catch (e: Exception) {
//            Log.d("receipt", e.toString())
//            return Event.FAILURE
//
//        }
//
//
//        return Event.SUCCESS
    }

    override suspend fun becomeAdmin(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long, age: Int
        try {
            val receipt = contract.createAdmin(
                BigInteger("${userOrAdmin._aadharNo.toString()}"),
                BigInteger("${userOrAdmin._age.toString()}")
            ).sendAsync()
            receipt.await()
            // add the useroradmin to the firebase realtime database in alladmins
            allSingeltonObjects.referenceToAllAdmins.child("${userOrAdmin._aadharNo}")
                .setValue(userOrAdmin).await()
            // aadharNo->UserOrAdmin
            // adding the Same in allvoters list also
            allSingeltonObjects.referenceToAllVoters.child("${userOrAdmin._aadharNo}")
                .setValue(userOrAdmin).await()
            Log.d("receipt", receipt.toString())
        } catch (e: Exception) {
            Log.d("receipt", e.toString())
            return Event.FAILURE

        }



        return Event.SUCCESS
    }

    override suspend fun deleteAdmin(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long
        try {
            val receipt = contract.deleteAdmin(
                BigInteger("${userOrAdmin._aadharNo.toString()}")
            ).sendAsync()
            receipt.await()
            // deleting the UserOrAdmin from the firebase realtime database in alladmins
            allSingeltonObjects.referenceToAllAdmins.child("${userOrAdmin._aadharNo}").removeValue()
                .await()
            Log.d("receipt", receipt.toString())


        } catch (e: Exception) {
            Log.d("receipt", e.toString())
            return Event.FAILURE

        }



        return Event.SUCCESS
    }


}