package com.dreadedlama.reply.smartreply.getSmartReply

import com.dreadedlama.reply.smartreply.R
import com.joaomgcd.taskerpluginlibrary.input.TaskerInputField
import com.joaomgcd.taskerpluginlibrary.input.TaskerInputRoot

@TaskerInputRoot
class GetSmartReplyInput @JvmOverloads constructor(
        @field:TaskerInputField("message", R.string.message) var message: String? = null
)