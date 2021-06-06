package com.dreadedlama.reply.smartreply.getSmartReply

import com.dreadedlama.reply.smartreply.R
import com.joaomgcd.taskerpluginlibrary.output.TaskerOutputObject
import com.joaomgcd.taskerpluginlibrary.output.TaskerOutputVariable

@TaskerOutputObject
class GetSmartReplies(
        @get:TaskerOutputVariable(VAR_IP, R.string.msg, R.string.msg_description) var replyToMessage: String?
){
    companion object {
        const val VAR_IP = "reply"
    }
}