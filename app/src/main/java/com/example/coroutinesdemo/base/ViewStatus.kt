package com.example.coroutinesdemo.base

import java.lang.Exception


sealed class ViewStatus {
    data class SUCCESS(val message : String) : ViewStatus()
    data class LOADING(val message : String) : ViewStatus()
    data class ERROR(val failure: Failure) : ViewStatus()

    //add data class for failure and do the appropriate error handling

    //use this generic object when o customization needed
    companion object {

        val LOADING = ViewStatus.LOADING("loading......")
        val SUCCESS = ViewStatus.SUCCESS("SUCCESS")

    }

}