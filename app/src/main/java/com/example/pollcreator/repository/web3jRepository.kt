package com.example.pollcreator.repository

import com.example.pollcreator.dataclass.Candidate
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.dataclass.Event
import com.example.pollcreator.dataclass.UserOrAdmin


interface web3jRepository  {


    suspend fun createPoll(poll: Poll) : Event

    suspend fun castVote(pollid : String , aadharNoOfCandidate : String,gender : String , aadharNoOfVoter : String) : Event

    suspend fun getAllPollCreatedByAdmin ( aadharNoOfAdmin : String) : List<Poll>

    suspend fun getAllUpcomingPoll( age : Int) : List<Poll>    // give all the poll that are active and the eligible age is lesser or equal to the age

    suspend fun getPreviousPoll(aadharNoOfVoter: String) : List<Poll>

    suspend fun becomeCandidateOfPoll(candidate: Candidate) : Event

    suspend fun becomeVoter(userOrAdmin: UserOrAdmin) : Event

    suspend fun becomeAdmin(userOrAdmin: UserOrAdmin) : Event

    suspend fun deleteAdmin(userOrAdmin: UserOrAdmin) : Event







}