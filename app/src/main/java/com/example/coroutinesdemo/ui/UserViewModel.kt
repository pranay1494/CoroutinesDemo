package com.example.coroutinesdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coroutinesdemo.api.model.User
import com.example.coroutinesdemo.base.BaseViewModel
import com.example.coroutinesdemo.base.CoroutineDispatcherProvider
import com.example.coroutinesdemo.base.ViewStatus
import com.example.coroutinesdemo.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

open class UserViewModel @Inject constructor(private val repository: UserRepository, @Named("default") private val defaultDispatcher: CoroutineDispatcher) : BaseViewModel() {
    private val userData = MutableLiveData<DisplayData>()

    fun getData(): LiveData<DisplayData> = userData

    fun fetchUserData(username: String) = viewModelScope.launch(defaultDispatcher) {
        viewStatus.postValue(ViewStatus.LOADING)
        try {
            val user = repository.fetchRepository("pranay1494")
            userData.postValue(getUserDisplayData(user))
        } catch (e: Exception) {
            handleError(e)
        } finally {
            viewStatus.postValue(ViewStatus.SUCCESS)
        }
    }

    private fun getUserDisplayData(user: User): DisplayData = DisplayData(name = user.name?:"", avatarUrl = user.avatarUrl?:"")
    data class DisplayData(val name :String = "",val avatarUrl:String = "")
}