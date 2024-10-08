package com.example.pollcreator.dataclass

data class allAdminObj(
    val _aadharNo: Long,
    val _isRegisteredAsAdmin: Boolean,
    val _pollsCreated : MutableList<Double>,
    val _pollsParticipated : MutableList<Double>
)
