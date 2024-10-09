package com.example.pollcreator.dataclass

import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


//   taking the day and startTime and endTime in individual inputs

data class Poll(
    val _name: String = "Name Of Poll",
    val _pollId: Double,        // aadharNo followed by no.Of Polls created by the user
    val _pollCreatedBy : Long,        //aadhar no of the admin who created the poll
    val _agendaOfPoll: String,
    val _listOfCandidate : MutableList<PollResultObj> = mutableListOf(),       // this will store the list of candidate with the vote they have gotten
    val _eligibleVoterAge : Int = 18,
    val _noOfMaleVoter : Long = 0,
    val _noOfFemaleVoter: Long  =0,
    val _startTime : Long = 0,               //this is the unix timestamp to ist
    val _endTime : Long=0,
    val _listOfVoterWhoHaveVoted : MutableList<Long> = mutableListOf<Long>()
)






//    private var pollId: Double = 0.0      // aadharNo followed by no.of polls created by the user