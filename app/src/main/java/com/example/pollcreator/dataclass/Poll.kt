package com.example.pollcreator.dataclass

import android.util.Log
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


//   taking the day and startTime and endTime in individual inputs

data class Poll(
    val _pollId: Double,
    val _pollCreatedBy : Long,
    val _agendaOfPoll: String,
    val _listOfCandidate : ArrayList<PollResultObj>,       // this will store the list of candidate with the vote they have gotten
    val _eligibleVoterAge : Int = 18,
    val _noOfMaleVoter : Long = 0,
    val _noOfFemaleVoter: Long  =0,
    val _startTime : LocalDateTime = LocalDateTime.now() ,               // using the localDateTime of java to store the starting and ending time of the polls
    val _endTime : LocalDateTime = LocalDateTime.now()
)
//{
//
//
//    private var pollId: Double = 0.0      // aadharNo followed by no.of polls created by the user
//    private var agendaOfPoll: String = ""
//    private var listOfParticipants: ArrayList<Participant> = ArrayList()
//    private var dateOfPoll: LocalDate = LocalDate.of(_year, _month, _day)
//    private var startTime: LocalTime = LocalTime.of(_startHour, _startMinute , _startSec)
//    private var endTime: LocalTime = LocalTime.of(_endHour, _endMinute , _endSec)
//    private var eligibleVoterAge : Int =18
//    private var noOfPollCreated : Int = 0
//    private var pollResult : ArrayList<PollResultObj> = ArrayList()
//    private var femaleVoter : Long = 0
//    private var maleVoter : Long = 0
//    private var listOfVotersThatHaveVoted : ArrayList<Long> = ArrayList()      // check with aadharNo of the voter
//
//
//    init {
//        pollId = _pollId
//        agendaOfPoll = _agendaOfPoll
//        noOfPollCreated++
//
//    }
//
//
//    // implement here voting in the blockchain and fetching data from there
//
//    public fun vote(voterIdentity : UserOrAdmin, participant: Participant, localDate: LocalDate = LocalDate.now(), localTime: LocalTime = LocalTime.now()) {
//        if(localDate == dateOfPoll && localTime.isAfter(startTime) && localTime.isBefore(endTime) && !listOfParticipants.contains(participant)  && !listOfVotersThatHaveVoted.contains(voterIdentity.getAadharNo())) {
//            if(voterIdentity.getGender()==Gender.MALE) {
//                // add here the blockchain implementations
//                maleVoter++
//            }else{
//                femaleVoter++
//            }
//            listOfVotersThatHaveVoted.add(voterIdentity.getAadharNo())
//            Log.d("vote", "the vote has been casted successfully")
//
//        }else {
//            Log.d("vote","the user already has voted")
//        }
//
//    }
//
//
//
//
//    public fun getPollId(): Double {
//        return pollId
//    }
//
//
//
//}