package com.example.pollcreator.dataclass

data class allAdminObj(
    val _aadharNo: String,
    val _isRegisteredAsAdmin: Boolean,
    val _pollsCreated : MutableList<String>,
    val _pollsParticipated : MutableList<String>
)
