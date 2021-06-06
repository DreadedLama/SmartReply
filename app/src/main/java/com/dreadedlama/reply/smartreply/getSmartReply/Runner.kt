package com.dreadedlama.reply.smartreply.getSmartReply

import android.content.Context
import com.google.mlkit.nl.smartreply.SmartReply
import com.google.mlkit.nl.smartreply.SmartReplySuggestionResult
import com.google.mlkit.nl.smartreply.TextMessage
import com.joaomgcd.taskerpluginlibrary.action.TaskerPluginRunnerAction
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResult
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResultSucess
import java.util.*

class GetIPRunner : TaskerPluginRunnerAction<GetSmartReplyInput, GetSmartReplies>() {

    //    private val smartReply = FirebaseNaturalLanguage.getInstance().smartReply
    //    private var conversation = ArrayList<FirebaseTextMessage>()
    private val smartReply = SmartReply.getClient()
    override fun run(context: Context, input: TaskerInput<GetSmartReplyInput>): TaskerPluginResult<GetSmartReplies> {

        val chatHistory = ArrayList<TextMessage>()
        if (input.regular.message != null) {
            val messageReply: String = input.regular.message.toString();
            chatHistory.add(TextMessage.createForRemoteUser(messageReply,
                    0, UUID.randomUUID().toString()))
        }
        var smartReplies: String? = null
        smartReply.suggestReplies(chatHistory)
                .addOnSuccessListener { result ->
                    if (result.getStatus() == SmartReplySuggestionResult.STATUS_NOT_SUPPORTED_LANGUAGE) {
                        smartReplies = "No smart replies found"
                    } else if (result.getStatus() == SmartReplySuggestionResult.STATUS_SUCCESS) {
                        for (suggestion in result.suggestions) {
                            smartReplies = if (smartReplies.isNullOrBlank())
                                suggestion.text
                            else
                                smartReplies + "\n" + suggestion.text
                        }
                    }
                }
                .addOnFailureListener {
                    smartReplies = "No smart replies found"
                }
        Thread.sleep(750)
        return TaskerPluginResultSucess(GetSmartReplies(smartReplies), null)
    }

//    private fun addMessage(text : String){
//        conversation.add(
//                FirebaseTextMessage.createForRemoteUser(
//                        text, System.currentTimeMillis(), nameText.text.toString()))
//    }
}