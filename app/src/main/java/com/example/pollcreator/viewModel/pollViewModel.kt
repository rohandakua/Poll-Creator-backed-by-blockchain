package com.example.pollcreator.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.rememberTransition
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
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
import kotlin.contracts.contract

class pollViewModel(pollId:String) : ViewModel() {     // this is the viewModel for each poll
    // init with the pollId
    private var _pollId=mutableStateOf("")
    private var _poll=mutableStateOf(Poll())
    private var _showAnimation=mutableStateOf(false)

    val pollId: State<String> = _pollId
    val poll: State<Poll> = _poll
    val showAnimation: State<Boolean> = _showAnimation

    fun setPollId(value:String){
        _pollId.value=value
    }

    fun setPoll(value:Poll){
        _poll.value=value
    }
    fun setShowAnimation(value:Boolean){
        _showAnimation.value=value
    }

    // this data is for participate_in_poll_confirmation screen and is used for creatingCandidate
    private var _name = mutableStateOf("")
    private var _agenda = mutableStateOf("")
    private var _yearlyIncome = mutableStateOf("")
    private var _age = mutableStateOf("")
    private var _gender = mutableStateOf("")
    private var _password = mutableStateOf("")
    private var _aadhar = mutableStateOf("")

    val Name: State<String> get()= _name
    val Agenda: State<String> get()= _agenda
    val YearlyIncome: State<String> get()= _yearlyIncome
    val Age: State<String> get()= _age
    val GenderVM: State<String> get()= _gender
    val Password: State<String> get()= _password
    val Aadhar: State<String> get()= _aadhar

    fun setAgenda(value:String){
        _agenda.value=value
    }
    fun setName(value:String){
        _name.value=value
    }
    fun setYearlyIncome(value:String){
        _yearlyIncome.value=value
    }
    fun setAge(value:String) {
        _age.value = value
    }
    fun setGender(value:String){
        _gender.value=value
    }
    fun setPassword(value:String){
        _password.value=value
    }
    fun setAadhar(value:String){
        _aadhar.value=value
    }
    // this data is for participate_in_poll_confirmation screen and is used for creatingCandidate




    init {
        setPollId(pollId)
        Log.d("pollViewModel","initialised for pollid ${pollId}")
        getPollDetailsFromOnline()
    }

    fun getPollDetailsFromOnline(id:String = _pollId.value):Poll{
        var pollObj : Poll = Poll(_agendaOfPoll = "", _pollCreatedBy = _pollId.value.toString().substring(0,12), _pollId = _pollId.value)
        try {
            allSingeltonObjects.referenceToPolls.child(id.toString()).get()
                .addOnSuccessListener { dataSnapshot ->
                    if (dataSnapshot != null) {
                        pollObj = dataSnapshot.getValue(Poll::class.java) ?: Poll(
                            _agendaOfPoll = "",
                            _pollCreatedBy = _pollId.value.toString().substring(0, 12),
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
        aadharNoOfCandidate : String,
        gender : String,
        aadharNoOfVoter : String,
        password:String,
        pollId:String
    ){
        getPollDetailsFromOnline()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                allSingeltonObjects.web3jDataModel.castVote(
                    pollid = pollId,
                    aadharNoOfCandidate = aadharNoOfCandidate,
                    gender = gender,
                    aadharNoOfVoter = aadharNoOfVoter
                )
            }
        }catch (e :Exception){
            Log.d("castVote PollViewModel","$e")
        }



    }
    fun getDetailsOfPoll():Poll{
        getPollDetailsFromOnline()
        return _poll.value
    }

    fun getCandidateList():MutableList<PollResultObj>{
        getPollDetailsFromOnline()
        return _poll.value._listOfCandidate.values.toMutableList()
    }

    fun getResultOfPoll():MutableList<PollResultObj>{
        val candidateList = getCandidateList()
        // make this list in descending order of noOfVote
        candidateList.sortByDescending { it.noOfVote }
        return candidateList
    }

    fun becomeCandidate(
        context: Context
    ){
        getPollDetailsFromOnline()

        // adding the conditions to check
        if(
            _name.value.isEmpty() ||
            _agenda.value.isEmpty() ||
            _yearlyIncome.value.isEmpty() ||
            _age.value.isEmpty() ||
            _gender.value.isEmpty() ||
            _aadhar.value.isEmpty() ||
            _aadhar.value.length!=12 ||
            _password.value!=allSingeltonObjects.profileViewModel.password.value
        ){
            Toast.makeText(context,"Please fill all the fields Carefully",Toast.LENGTH_SHORT).show()
            return
        }


        val candidate = Candidate(
            _pollId = _pollId.value,
            _aadharNo = _aadhar.value,
            _agenda = _agenda.value,
            _yearlyIncome = _yearlyIncome.value.toLong(),
            _age = _age.value.toInt(),
            _gender =  if(_gender.value == "male") Gender.MALE else Gender.FEMALE
        )
        CoroutineScope(Dispatchers.IO).launch {
            setShowAnimation(true)
            async { allSingeltonObjects.web3jDataModel.becomeCandidateOfPoll(candidate)}.await()
            setShowAnimation(false)

        }
    }


}