A tasker plugin that uses ML Kit for Smart Replies.

## Introduction

The Reply app uses the ML Kit smart replies feature to take a message as input and output suggest replies if available.
It is a tasker plugin and does not have any standalone use. The output will be save in %reply varaible.

## Getting Started

* In tasker task choose the plugin option and select "Reply"
* Input the message to get suggested smart replies
* The output is saved in %reply varaible as string with each suggesting in new line. 
  Sample output - "Hi
                   Bye
                   Hey There"
* If no smart reply is found, the %reply varaible is set to "No smart replies found"
