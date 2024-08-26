package com.example.pollcreator.dataclass

import android.util.Log

data class UserOrAdmin(
    val _aadharNo: Long=0,
    var _password: String="",
    val _gender: Gender = Gender.MALE,
    val _age: Int =0,
    val _name: String="",
    val adminOrNot : Boolean?=true,
    val pan : String? = null,
    val noOfPollCreated : Int?=0
)
//{
//
//    private var aadharNo: Long = 0
//    private var name: String = ""
//    private var age: Int = 0
//    private var password: String = ""
//    private var adminOrNot: Boolean = false
//    private var pan: String = ""
//    private var gender: Gender = Gender.MALE
//    private var noOfPollCreated: Int = 0
//
//    // admin must have the list of the poll that have been created by him
//    private var listOfPollCreated: ArrayList<Double> = ArrayList()
//
//    init {
//        aadharNo = _aadharNo
//        password = _password
//        gender = _gender
//        age = _age
//        name = _name
//
//    }
//
//    // method to change the password of the account
//    public fun changePassword(oldPassword: String, newPassword: String) {
//        if (newPassword.length >= 8 && password.equals(oldPassword)) {
//            password = newPassword
//            Log.d(
//                "changePassword",
//                " the password has been changed of the account having aadhar no $aadharNo"
//            )
//        }
//
//    }
//
//    //method to become an admin
//    public fun becomeAdmin(_pan: String) {
//        if (pan.length == 10 && !adminOrNot) {
//            adminOrNot = true
//            pan = _pan
//            Log.d(
//                "becomeAdmin",
//                " the account having aadhar number $aadharNo has been made an admin"
//            )
//        }
//    }
//
//    public fun getAadharNo(): Long {
//        return aadharNo
//    }
//
//    public fun getGender(): Gender {
//        return gender
//    }
//    fun countDigits(number: Int): Double {
//        var num = if (number == 0) 1 else Math.abs(number)
//        var count:Double = 0.0
//        while (num > 0) {
//            num /= 10
//            count++
//        }
//        return count
//    }
//
//    // method to create a poll
//    public fun createPoll(
//        _agendaOfPoll: String,
//        _year: Int = 2020,
//        _month: Int = 12,
//        _day: Int = 1,
//        _startHour: Int = 0,
//        _startMinute: Int = 0,
//        _startSec: Int = 0,
//        _endHour: Int = 0,
//        _endMinute: Int = 0,
//        _endSec: Int = 0,
//        _eligibleVoter : Int = 18
//    ) {
//        if (adminOrNot) {
//            noOfPollCreated++
//            var pollId = aadharNo*(Math.pow(10.0 , countDigits(noOfPollCreated))) + noOfPollCreated   // creating the poll id
//            var newPoll : Poll = Poll(pollId,_agendaOfPoll,_year,_month,_day,_startHour,_startMinute,_startSec,_endHour,_endMinute,_endSec,_eligibleVoter)
//            listOfPollCreated.add(newPoll.getPollId())
//
//            // add this in the list of ongoing_polls
//            // add here the blockchain implementations for creating a poll
//
//            Log.d("createPoll", "the poll has been created successfully")
//
//        }else {
//            Log.d("createPoll","the poll creation was unsuccessful")
//        }
//
//
//    }
//
//
//    public fun deletePoll (_pollIdToBeDeleted : Double){
//        if(listOfPollCreated.contains(_pollIdToBeDeleted)){
//            listOfPollCreated.remove(_pollIdToBeDeleted)
//
//            // add here the blockchain implementation of deleting the poll
//            // delete the poll from ongoing_poll list
//
//            Log.d("deletePoll", "the poll has been deleted successfully")
//        }else {
//            Log.d("deletePoll","the poll deletion was unsuccessful")
//        }
//
//
//    }
//
//
//}