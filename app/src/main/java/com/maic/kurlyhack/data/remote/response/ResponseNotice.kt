package com.maic.kurlyhack.data.remote.response

data class ResponseNotice(
    val messages: ArrayList<MessageData>
)

data class MessageData(
    val messageId: Int,
    val content: String,
    val fullLocation: String,
    val time: Long
)
