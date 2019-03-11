package com.example.coroutinesdemo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

open class BaseViewModel : ViewModel(){

    //observing mutablelivedata for now change it later
     var viewStatus : MutableLiveData<ViewStatus> = MutableLiveData()

    fun handleError(e: Exception) {
        try {
            if (e is HttpException) {
                if(e.code() == 401)
                    onFailure(Failure.AuthenticationError("Your session has expired!"))
                else
                    onFailure(Failure.HttpFailure(e.message(),e))
            } else if (e is IOException) {
                onFailure(Failure.NetworkConnection("You are not connected to Internet!"))
            } else if (e is Failure) {
                onFailure(e)
            }  else {
                onFailure(Failure.ServerError("Something went wrong Please try again later"))
            }
            e.printStackTrace()
        } catch (e: Throwable) {
            e.printStackTrace()
            onFailure(Failure.ServerError("Something went wrong Please try again later"))
        }
    }

    private fun onFailure(failure: Failure) {
        viewStatus.postValue(ViewStatus.ERROR(failure))
    }
}
