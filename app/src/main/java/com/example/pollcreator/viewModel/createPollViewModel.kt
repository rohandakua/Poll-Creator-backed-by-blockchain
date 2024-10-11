package com.example.pollcreator.viewModel

import android.content.Context
import android.util.Log
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
    private var _reqAge = mutableStateOf<String>("")

    val name : State<String> get() = _name
    val agenda : State<String> get() = _agenda
    val date : State<String> get() = _date
    val startingTime : State<String> get() = _startingTime
    val endingTime : State<String> get() = _endingTime
    val reqAge : State<String> get() = _reqAge

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
    fun setReqAge(value:String){
        _reqAge.value = value
    }

    fun createPoll(context: Context,poll: Poll){
        try{
            viewModelScope.launch {
                allSingeltonObjects.web3jDataModel.createPoll(poll)
            }
            // add this to polls object and also add the pollId to alladmin
            allSingeltonObjects.referenceToPolls.child(poll._pollId.toString()).setValue(poll)
            allSingeltonObjects.referenceToAllAdmins.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("_pollsCreated").child(poll._pollId.toString()).setValue(poll._pollId.toString())




        }catch (e:Exception){
            Log.d("error",e.toString())
        }
    }










}