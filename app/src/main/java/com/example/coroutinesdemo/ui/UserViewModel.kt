package com.example.coroutinesdemo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutinesdemo.api.model.User
import com.example.coroutinesdemo.base.BaseViewModel
import com.example.coroutinesdemo.base.ViewStatus
import com.example.coroutinesdemo.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {
    private val userData = MutableLiveData<User>()

    suspend fun fetchUserData(username : String){
        viewStatus.postValue(ViewStatus.LOADING)
        val user = repository.fetchRepository(UserParams(username)).await()
        userData.postValue(user)
        viewStatus.postValue(ViewStatus.SUCCESS)
    }

    data class UserParams(val name : String)
}