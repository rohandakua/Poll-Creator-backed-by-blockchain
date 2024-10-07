package com.example.pollcreator.onlineStorage

import android.util.Log
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.contract.PollCreator
import com.example.pollcreator.dataclass.Candidate
import com.example.pollcreator.dataclass.Event
import com.example.pollcreator.dataclass.Poll
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
    val privateKey = allSingeltonObjects.privateKeyViewModelObject.privateKey
    val web3j: Web3j = Web3j.build(HttpService(allSingeltonObjects.sepoliaUrl))
    var credential = Credentials.create(privateKey.toString())
    lateinit var contract: PollCreator


    suspend fun createWeb3jObject() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
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
        if (allSingeltonObjects.profileViewModel.isAdmin.value) {
            // creating the poll in web3j
            try {
                val receipt = contract.createPoll(
                    BigInteger("${poll._pollId}"),
                    poll._agendaOfPoll,
                    BigInteger("${poll._startTime.toString()}"),
                    BigInteger("${poll._endTime.toString()}"),
                    BigInteger("${poll._eligibleVoterAge.toString()}"),
                    BigInteger("${allSingeltonObjects.profileViewModel.aadharNo.toString()}")
                ).sendAsync()
                receipt.await()
                Log.d("receipt", receipt.toString())
                allSingeltonObjects.referenceToPolls.child("${poll._pollId}").setValue(poll)
                    .await()  // adding the poll to the firebase realtime database
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
        pollid: Double,
        aadharNoOfCandidate: Long,
        gender: String,
        aadharNoOfVoter: Long
    ): Event {
        // adding the vote to blockchain
        try {
            val receipt = contract.castVote(
                BigInteger("${pollid.toString()}"),  //  find the index of the candidate in the list of candidate of the poll
                BigInteger("${aadharNoOfCandidate.toString()}"),
                BigInteger("${aadharNoOfVoter.toString()}")
            ).sendAsync()
            receipt.await()
            Log.d("receipt", receipt.toString())
        } catch (e: Exception) {
            Log.d("castVote", e.toString())
            return Event.FAILURE
        }


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


        return Event.SUCCESS

    }

    override suspend fun getAllPollCreatedByAdmin(aadharNoOfAdmin: Long): List<Poll> {
        var pollList: MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for (poll in snapShot.children) {
            val pollTemp = poll.getValue(Poll::class.java)
            if (pollTemp != null && pollTemp._pollCreatedBy.toLong()==aadharNoOfAdmin) {
                pollList.add(pollTemp)
            }

        }
        pollList.sortByDescending { it._startTime }   // giving the value in descending order by startTime
        return pollList

    }

    override suspend fun getAllUpcomingPoll(age: Int): List<Poll> {

        // adding the poll from firebase realtime database to the list of poll
        val pollList: MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for (poll in snapShot.children) {
            val pollTemp = poll.getValue(Poll::class.java)
            pollTemp?.let {
                if (pollTemp._eligibleVoterAge <= age && allSingeltonObjects.helperFunctions.convertToUnixTimestampIST(
                        Date()
                    )
                    <= allSingeltonObjects.helperFunctions.convertToUnixTimestampIST(pollTemp._startTime)
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
    override suspend fun getPreviousPoll(aadharNoOfVoter: Long): List<Poll> {
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
        /*pollid: Double,
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
            Log.d("receipt", receipt.toString())


        } catch (e: Exception) {
            Log.d("receipt", e.toString())
            return Event.FAILURE
        }
        allSingeltonObjects.referenceToPolls.child("${candidate._pollId}").child("_listOfCandidate")
            .child("${candidate._aadharNo}").setValue(candidate).await()
        // inserting in the list of candidate of the poll
        // poll id -> { , , , , _listOfPoll -> { candidate_aadharNo -> candidate } }

        return Event.SUCCESS


    }

    override suspend fun becomeVoter(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long, age: Int
        try {
            val receipt = contract.createVoter(
                BigInteger("${userOrAdmin._aadharNo.toString()}"),
                BigInteger("${userOrAdmin._age.toString()}")
            ).sendAsync()
            receipt.await()
            Log.d("receipt", receipt.toString())

        } catch (e: Exception) {
            Log.d("receipt", e.toString())
            return Event.FAILURE

        }
        // adding the useroradmin to the firebase realtime database in allvoters
        allSingeltonObjects.referenceToAllVoters.child("${userOrAdmin._aadharNo}")
            .setValue(userOrAdmin).await()
        // aadharNo->UserOrAdmin

        return Event.SUCCESS
    }

    override suspend fun becomeAdmin(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long, age: Int
        try {
            val receipt = contract.createAdmin(
                BigInteger("${userOrAdmin._aadharNo.toString()}"),
                BigInteger("${userOrAdmin._age.toString()}")
            ).sendAsync()
            receipt.await()
            Log.d("receipt", receipt.toString())
        } catch (e: Exception) {
            Log.d("receipt", e.toString())
            return Event.FAILURE

        }
        // add the useroradmin to the firebase realtime database in alladmins
        allSingeltonObjects.referenceToAllAdmins.child("${userOrAdmin._aadharNo}")
            .setValue(userOrAdmin).await()
        // aadharNo->UserOrAdmin
        // adding the Same in allvoters list also
        allSingeltonObjects.referenceToAllVoters.child("${userOrAdmin._aadharNo}")
            .setValue(userOrAdmin).await()


        return Event.SUCCESS
    }

    override suspend fun deleteAdmin(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long
        try {
            val receipt = contract.deleteAdmin(
                BigInteger("${userOrAdmin._aadharNo.toString()}")
            ).sendAsync()
            receipt.await()
            Log.d("receipt", receipt.toString())


        } catch (e: Exception) {
            Log.d("receipt", e.toString())
            return Event.FAILURE

        }
        // deleting the UserOrAdmin from the firebase realtime database in alladmins
        allSingeltonObjects.referenceToAllAdmins.child("${userOrAdmin._aadharNo}").removeValue()
            .await()


        return Event.SUCCESS
    }


}