package com.dreadedlama.reply.smartreply.getSmartReply

import com.dreadedlama.reply.smartreply.ActivityConfigTasker
import com.dreadedlama.reply.smartreply.R
import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfig
import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfigHelper
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput
import kotlinx.android.synthetic.main.activity_config_get_smart_reply.*

class GetIPHelper(config: TaskerPluginConfig<GetSmartReplyInput>) : TaskerPluginConfigHelper<GetSmartReplyInput, GetSmartReplies, GetIPRunner>(config) {
    override val runnerClass = GetIPRunner::class.java
    override val inputClass = GetSmartReplyInput::class.java
    override val outputClass = GetSmartReplies::class.java

}

class ActivityConfigGetSmartReply : ActivityConfigTasker<GetSmartReplyInput, GetSmartReplies, GetIPRunner, GetIPHelper>() {
    //Overrides
    override fun getNewHelper(config: TaskerPluginConfig<GetSmartReplyInput>) = GetIPHelper(config)

    override fun assignFromInput(input: TaskerInput<GetSmartReplyInput>) = input.regular.run {
        editTextSeparator.setText(message)
    }

    override val inputForTasker get() = TaskerInput(GetSmartReplyInput(editTextSeparator.text.toString()))
    override val layoutResId = R.layout.activity_config_get_smart_reply

}