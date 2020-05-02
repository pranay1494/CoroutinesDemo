package com.example.coroutinesdemo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.io.IOException

open class BaseViewModel : ViewModel(){

    private var retry: () -> Unit = {}

     var viewStatus : MutableLiveData<ViewStatus> = MutableLiveData()

    protected val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    fun showLoading(){
        viewStatus.postValue(ViewStatus.LOADING)
    }

    fun postSuccess(){
        viewStatus.postValue(ViewStatus.SUCCESS)
    }

    fun handleError(e: Throwable) {
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

    private val EMPTY: () -> Unit = {}

    protected fun setRetryLogicInCaseOfError(retry: () -> Unit = {}) {this.retry = retry}

    private fun onFailure(failure: Failure) {
        failure.retry = retry
        if (failure.retry != EMPTY){
            failure.shouldShowRetryBtn = true
        }
        viewStatus.postValue(ViewStatus.ERROR(failure))
    }
}
