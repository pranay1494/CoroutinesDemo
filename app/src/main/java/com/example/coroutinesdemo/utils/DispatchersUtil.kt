package com.example.coroutinesdemo.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * change this to return dispatchers and add it to your network module
 */
class DispatchersUtil {
    val main = AndroidSchedulers.mainThread()
    val io = Schedulers.io()
}