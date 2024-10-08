package com.example.pollcreator.viewModel

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Candidate
import com.example.pollcreator.dataclass.Gender
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.dataclass.PollResultObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class pollViewModel(pollId:Double) : ViewModel() {     // this is the viewModel for each poll
    // init with the pollId
    private var _pollId=mutableStateOf(0.0)
    private var _poll=mutableStateOf(Poll(_agendaOfPoll = "", _pollCreatedBy = _pollId.toString().substring(0,12).toLong(), _pollId = _pollId.value))
    private var _showAnimation=mutableStateOf(false)

    val pollId: State<Double> = _pollId
    val poll: State<Poll> = _poll
    val showAnimation: State<Boolean> = _showAnimation

    fun setPollId(value:Double){
        _pollId.value=value
    }

    fun setPoll(value:Poll){
        _poll.value=value
    }
    fun setShowAnimation(value:Boolean){
        _showAnimation.value=value
    }

    init {
        setPollId(pollId)
        getPollDetailsFromOnline()
    }

    fun getPollDetailsFromOnline(id:Double = _pollId.value):Poll{
        var pollObj : Poll = Poll(_agendaOfPoll = "", _pollCreatedBy = _pollId.value.toString().substring(0,12).toLong(), _pollId = _pollId.value)
        try {
            allSingeltonObjects.referenceToPolls.child(id.toString()).get()
                .addOnSuccessListener { dataSnapshot ->
                    if (dataSnapshot != null) {
                        pollObj = dataSnapshot.getValue(Poll::class.java) ?: Poll(
                            _agendaOfPoll = "",
                            _pollCreatedBy = _pollId.value.toString().substring(0, 12).toLong(),
                            _pollId = _pollId.value
                        )
                        if (pollObj != null) {
                            setPoll(pollObj)
                        }
                    } else {
                        Log.d("pollViewModel", "poll not found")
                    }

                }.addOnFailureListener {
                Log.d("pollViewModel", "failure")
            }
        }catch (e:Exception){
            Log.d("pollViewModel", "exception")

        }
        return pollObj

    }

    fun castVote(
        aadharNoOfCandidate : Long,
        gender : String,
        aadharNoOfVoter : Long,
        password:String,
        pollId:Double
    ){


    }

    fun getDetailsOfPoll():Poll{
        getPollDetailsFromOnline()
        return _poll.value
    }

    fun getCandidateList():MutableList<PollResultObj>{
        getPollDetailsFromOnline()
        return _poll.value._listOfCandidate
    }

    fun getResultOfPoll():MutableList<PollResultObj>{
        val candidateList = getCandidateList()
        // make this list in descending order of noOfVote
        candidateList.sortByDescending { it.noOfVote }
        return candidateList
    }

    fun becomeCandidate(
        aadharNo : Long,
        mainAgenda : String,
        yearlyIncome : String,
        age : String,
        gender : String
    ){
        getPollDetailsFromOnline()
        val candidate = Candidate(
            _pollId = _pollId.value,
            _aadharNo = aadharNo,
            _agenda = mainAgenda,
            _yearlyIncome = yearlyIncome.toLong(),
            _age = age.toInt(),
            _gender =  if(gender == "Male") Gender.MALE else Gender.FEMALE
        )
        CoroutineScope(Dispatchers.IO).launch {
            setShowAnimation(true)
            async { allSingeltonObjects.web3jDataModel.becomeCandidateOfPoll(candidate)}.await()
            setShowAnimation(false)

        }
    }

    fun showAnimation(){
        TODO()
    }
}