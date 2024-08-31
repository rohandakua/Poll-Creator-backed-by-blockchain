package com.example.pollcreator.onlineStorage

import android.content.Context
import android.util.Log
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.LaunchedEffect
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Candidate
import com.example.pollcreator.dataclass.Event
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.repository.metaMaskRepository
import com.example.pollcreator.rubbish.helperFunctions

import io.metamask.androidsdk.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import java.util.Date
import kotlin.coroutines.coroutineContext


class metaMaskDataModel(localContext: Context) : metaMaskRepository {
    override suspend fun createPoll(poll: Poll): Event {


        allSingeltonObjects.referenceToPolls.child("${poll._pollId}").setValue(poll).await()  // adding the poll to the firebase realtime database
        return Event.SUCCESS

    }

    override suspend fun castVote(pollid: Double, aadharNoOfCandidate: Long,gender : String , aadharNoOfVoter : Long): Event {
        // adding the aadhar no of the Voter to the list of voter who have voted
        allSingeltonObjects.referenceToPolls.child("${pollid}").child("_listOfVoterWhoHaveVoted").child("${aadharNoOfVoter}").setValue(aadharNoOfVoter).await()
        // adding a vote to gender wise list
        if(gender == "male"){
            allSingeltonObjects.referenceToPolls.child("${pollid}").child("_noOfMaleVoter").setValue(allSingeltonObjects.referenceToPolls.child("${pollid}")
                .child("_noOfMaleVoter").get().await().value.toString().toLong()+1).await()
        }else{
            allSingeltonObjects.referenceToPolls.child("${pollid}").child("_noOfFemaleVoter").setValue(allSingeltonObjects.referenceToPolls.child("${pollid}")
                .child("_noOfFemaleVoter").get().await().value.toString().toLong()+1).await()
        }

        //adding the vote to the pollresultobject
        allSingeltonObjects.referenceToPolls.child("${pollid}").child("_listOfPollResult").child("${aadharNoOfCandidate}").child("noOfVote")
            .setValue(allSingeltonObjects.referenceToPolls.child("${pollid}").child("_listOfPollResult").child("${aadharNoOfCandidate}")
                .child("noOfVote").get().await().value.toString().toLong()+1).await()


        return Event.SUCCESS

    }

    override suspend fun getAllPollCreatedByAdmin(aadharNoOfAdmin: Long): List<Poll> {
        TODO()
    }

    override suspend fun getAllUpcomingPoll(age: Int): List<Poll> {

        // adding the poll from firebase realtime database to the list of poll
        val pollList : MutableList<Poll> = mutableListOf()
        val snapShot = allSingeltonObjects.referenceToPolls.get().await()
        for(poll in snapShot.children){
            val pollTemp = poll.getValue(Poll::class.java)
            pollTemp?.let{
                if (pollTemp._eligibleVoterAge <= age && allSingeltonObjects.helperFunctions.convertToUnixTimestampIST(Date())
                    <= allSingeltonObjects.helperFunctions.convertToUnixTimestampIST(pollTemp._startTime)    ) {
                    pollList.add(pollTemp)
                }
            }
        }
        pollList.sortBy { it._startTime }   // giving the value in ascending order by startTime

        return pollList
    }

    override suspend fun getPreviousPoll(aadharNoOfVoter: Long): List<Poll> {
        TODO()
    }

    override suspend fun becomeCandidateOfPoll( candidate: Candidate ): Event {
        /*pollid: Double,
        aadharNoOfAdmin: Long,
        age: Int,
        agendaOfPoll: String
         */

        allSingeltonObjects.referenceToPolls.child("${candidate._pollId}").child("_listOfCandidate").child("${candidate._aadharNo}").setValue(candidate).await()
        // inserting in the list of candidate of the poll
        // poll id -> { , , , , _listOfPoll -> { candidate_aadharNo -> candidate } }

        return Event.SUCCESS



    }

    override suspend fun becomeVoter(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long, age: Int
        return Event.SUCCESS
    }

    override suspend fun becomeAdmin(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long, age: Int
        return Event.SUCCESS
    }

    override suspend fun deleteAdmin(userOrAdmin: UserOrAdmin): Event {
        //aadharNo: Long
        return Event.SUCCESS
    }

}