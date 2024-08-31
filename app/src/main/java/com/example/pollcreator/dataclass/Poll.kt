package com.example.pollcreator.dataclass

import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


//   taking the day and startTime and endTime in individual inputs

data class Poll(
    val _pollId: Double,        // aadharNo followed by no.Of Polls created by the user
    val _pollCreatedBy : Long,        //aadhar no of the admin who created the poll
    val _agendaOfPoll: String,
    val _listOfCandidate : ArrayList<PollResultObj>,       // this will store the list of candidate with the vote they have gotten
    val _eligibleVoterAge : Int = 18,
    val _noOfMaleVoter : Long = 0,
    val _noOfFemaleVoter: Long  =0,
    val _startTime : Date = Date(),               // using the localDateTime of java to store the starting and ending time of the polls
    val _endTime : Date = Date(),
    val _listOfVoterWhoHaveVoted : ArrayList<Long> = ArrayList<Long>()
)






//    private var pollId: Double = 0.0      // aadharNo followed by no.of polls created by the user