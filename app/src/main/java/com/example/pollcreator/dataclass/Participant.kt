package com.example.pollcreator.dataclass

data class Participant(
    val _pollId: Long,
    val _aadharNo: Long,
    val _age: Int,
    val _gender: Gender,
    val _yearlyIncome: Long,
    val _agenda: String
)

//{
//    private var pollId: Long = 0
//    private var aadharNo: Long = 0
//    private var age: Int = 0
//    private var gender: Gender = Gender.MALE
//    private var yearlyIncome: Long = 0
//    private var agenda: String = ""
//
//
//    init {
//        pollId = _pollId
//        aadharNo = _aadharNo
//        age = _age
//        gender = _gender
//        yearlyIncome = _yearlyIncome
//        agenda = _agenda
//    }
//
//
//
//
//}