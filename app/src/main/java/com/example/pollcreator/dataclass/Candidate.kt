package com.example.pollcreator.dataclass

data class Candidate(
    val _pollId: Double,
    val _aadharNo: Long,
    val _age: Int,
    val _gender: Gender,
    val _yearlyIncome: Long,
    val _agenda: String,
    val _name : String ="name of candidate"
)
