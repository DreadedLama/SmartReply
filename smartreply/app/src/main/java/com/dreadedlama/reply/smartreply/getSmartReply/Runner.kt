package com.dreadedlama.reply.smartreply.getSmartReply

import android.content.Context
import com.dreadedlama.reply.smartreply.R
import com.google.mlkit.nl.smartreply.SmartReply
import com.google.mlkit.nl.smartreply.SmartReplySuggestionResult
import com.google.mlkit.nl.smartreply.TextMessage
import com.joaomgcd.taskerpluginlibrary.action.TaskerPluginRunnerAction
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResult
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResultSucess
import java.util.*

class GetIPRunner : TaskerPluginRunnerAction<GetSmartReplyInput, GetSmartReplies>() {

    //A custom notification icon is set for the foreground notification the action will have if the app targets API 26 or above
    override val notificationProperties get() = NotificationProperties(iconResId = R.drawable.plugin)
    private val smartReply = SmartReply.getClient()
    override fun run(context: Context, input: TaskerInput<GetSmartReplyInput>): TaskerPluginResult<GetSmartReplies> {

        val chatHistory = ArrayList<TextMessage>()
        if(input.regular.message != null) {
            val messageReply: String = input.regular.message.toString();
            chatHistory.add(TextMessage.createForRemoteUser(messageReply,
                    0, UUID.randomUUID().toString()))
        }
        var smartReplies:String? =null
        smartReply.suggestReplies(chatHistory)
                .addOnSuccessListener { result ->
                    if (result.getStatus() == SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE) {
                        smartReplies = "No smart replies found"
                    } else if (result.getStatus() == SmartReplySuggestionResult.STATUS_SUCCESS) {
                        for (suggestion in result.suggestions) {
                            smartReplies = if(smartReplies.isNullOrBlank())
                                suggestion.text
                            else
                                smartReplies + "\n" +suggestion.text
                        }
                    }
                }
                .addOnFailureListener {
                    // Task failed with an exception
                    // ...
                    smartReplies = "No smart replies found"
                }
        Thread.sleep(750)
        return TaskerPluginResultSucess(GetSmartReplies(smartReplies), null)
    }
}