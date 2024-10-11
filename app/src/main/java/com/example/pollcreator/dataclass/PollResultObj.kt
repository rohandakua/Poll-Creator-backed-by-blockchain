package com.example.pollcreator.dataclass

data class PollResultObj(
    public var candidate: Candidate = Candidate(),
    public var noOfVote: Long = 0L

)