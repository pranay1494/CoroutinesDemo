package com.example.coroutinesdemo.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){

    //observing mutablelivedata for now change it later
     var viewStatus : MutableLiveData<ViewStatus> = MutableLiveData()

}
