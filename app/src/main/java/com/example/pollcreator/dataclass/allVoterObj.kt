package com.example.pollcreator.dataclass

data class allVoterObj(
    val _aadharNo : String,
    val _isAdmin:Boolean,
    val _pollsVotedOn:MutableList<String>


)
