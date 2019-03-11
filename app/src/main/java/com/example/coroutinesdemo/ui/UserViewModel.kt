package com.example.coroutinesdemo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coroutinesdemo.api.model.User
import com.example.coroutinesdemo.base.BaseViewModel
import com.example.coroutinesdemo.base.ViewStatus
import com.example.coroutinesdemo.repository.UserRepository
import java.lang.Exception
import javax.inject.Inject

class UserViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {
    private val userData = MutableLiveData<User>()

    fun getData(): LiveData<User> = userData

    suspend fun fetchUserData(username: String) {
        viewStatus.postValue(ViewStatus.LOADING)
        try {
            Log.d("current_thread", Thread.currentThread().name)
            val user = repository.fetchRepository(UserParams(username)).await()
            userData.postValue(user)
        } catch (e: Exception) {
            handleError(e)
        } finally {
            viewStatus.postValue(ViewStatus.SUCCESS)
        }
    }

    data class UserParams(val name: String)
}