package com.maic.kurlyhack.data.remote.response

data class ResponseRoundData(
    val rounds: ArrayList<RoundData>
)

data class RoundData(
    val roundId: Int,
    val centerId: Int,
    val centerRoundNumber: Int,
    val pickTodoCount: Int
)
