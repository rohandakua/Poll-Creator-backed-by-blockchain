package com.example.pollcreator.dataclass

import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


//   taking the day and startTime and endTime in individual inputs

data class Poll(
    val _name: String = "Name Of Poll",
    val _pollId: String="1010101011011",        // aadharNo followed by no.Of Polls created by the user
    val _pollCreatedBy : String="1010101011011",        //aadhar no of the admin who created the poll
    val _agendaOfPoll: String="agenda",
    val _listOfCandidate :Map<String, PollResultObj> = mapOf(),       // this will store the list of candidate with the vote they have gotten
    val _eligibleVoterAge : Int = 18,
    val _noOfMaleVoter : Long = 0,
    val _noOfFemaleVoter: Long  =0,
    val _startTime : Long = 0,               //this is the unix timestamp to ist
    val _endTime : Long=0,
    val _listOfVoterWhoHaveVoted : Map<String, String> = mapOf()
)



