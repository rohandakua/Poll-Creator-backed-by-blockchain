package com.example.pollcreator.dataclass

data class Candidate(
    val _pollId: String="fake",
    val _aadharNo: String="123412341234",
    val _age: Int=12,
    val _gender: Gender=Gender.MALE,
    val _yearlyIncome: Long=100,
    val _agenda: String="fake agenda",
    val _name : String ="name of candidate"
)
