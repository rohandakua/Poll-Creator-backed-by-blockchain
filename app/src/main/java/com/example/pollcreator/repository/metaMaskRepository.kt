package com.example.pollcreator.repository

import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.dataclass.Event



interface metaMaskRepository  {


    suspend fun createPoll(poll: Poll) : Event

    suspend fun castVote(pollid : Double , aadharNo : Long) : Event

    suspend fun getAllPollCreatedByAdmin ( aadharNo: Long) : List<Poll>

    suspend fun getAllUpcomingPoll( age : Int) : List<Poll>    // give all the poll that are active and the eligible age is lesser or equal to the age

    suspend fun getPreviousPoll() : List<Poll>

    suspend fun becomeCandidateOfPoll(pollid: Double , aadharNoOfAdmin: Long) : Event





}