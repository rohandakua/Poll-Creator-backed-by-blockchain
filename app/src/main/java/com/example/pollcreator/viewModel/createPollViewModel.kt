package com.example.pollcreator.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Poll
import kotlinx.coroutines.launch
import java.util.Date

class createPollViewModel:ViewModel() {
    private var _name = mutableStateOf("")
    private var _agenda = mutableStateOf("")
    private var _date = mutableStateOf("")
    private var _startingTime = mutableStateOf("")
    private var _endingTime = mutableStateOf("")
    private var _reqAge = mutableStateOf<Int?>(null)

    val name : State<String> get() = _name
    val agenda : State<String> get() = _agenda
    val date : State<String> get() = _date
    val startingTime : State<String> get() = _startingTime
    val endingTime : State<String> get() = _endingTime
    val reqAge : State<Int?> get() = _reqAge

    fun setName(value:String){
        _name.value = value
    }
    fun setAgenda(value:String){
        _agenda.value = value
    }
    fun setDate(value:String){
        _date.value = value
    }
    fun setStartingTime(value:String){
        _startingTime.value = value
    }
    fun setEndingTime(value:String){
        _endingTime.value = value
    }
    fun setReqAge(value:Int){
        _reqAge.value = value
    }

    fun createPoll(context: Context){
        if(_name.value.isEmpty() || _agenda.value.isEmpty() || _date.value.isEmpty() || _startingTime.value.isEmpty() || _endingTime.value.isEmpty() || _reqAge.value == null){
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
        try{
            val decimal = allSingeltonObjects.profileViewModel.noOfPollCreated.value.toDouble()* Math.pow(10.0,allSingeltonObjects.profileViewModel.noOfPollCreated.value.toLong().toString().length.toDouble())
            val start = allSingeltonObjects.helperFunctions.convertToUnixTimestamp(allSingeltonObjects.helperFunctions.convertToDate(_date.value,_startingTime.value)?: Date())
            val end = allSingeltonObjects.helperFunctions.convertToUnixTimestamp(allSingeltonObjects.helperFunctions.convertToDate(_date.value,_endingTime.value)?:Date())

            val poll = Poll(
                _name = _name.value,
                _pollId = allSingeltonObjects.profileViewModel.aadharNo.value.toDouble()+decimal,
                _pollCreatedBy = allSingeltonObjects.profileViewModel.aadharNo.value.toLong(),
                _agendaOfPoll = agenda.value,
                _eligibleVoterAge = reqAge.value.toString().toInt(),
                _startTime = start,
                _endTime = end
            )
            viewModelScope.launch {
                allSingeltonObjects.web3jDataModel.createPoll(poll)
            }


            // add this to polls object and also add the pollId to alladmin
            allSingeltonObjects.referenceToPolls.child(poll._pollId.toString()).setValue(poll)
            allSingeltonObjects.referenceToAllAdmins.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("_pollsCreated").child(poll._pollId.toString()).setValue(poll._pollId.toString())
            Toast.makeText(context, "Poll created successfully", Toast.LENGTH_SHORT).show()



        }catch (e:Exception){
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }










}