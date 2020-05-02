package com.example.coroutinesdemo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.coroutinesdemo.api.model.User
import com.example.coroutinesdemo.repository.UserRepository
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTestShould{

    @get:Rule val rule = InstantTaskExecutorRule()

    @get:Rule val dispatcherRule = CoroutineDispatcherRule()

    private lateinit var viewModel: UserViewModel
    @Mock lateinit var repository: UserRepository
    @Mock lateinit var observer: Observer<UserViewModel.DisplayData>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel(repository,dispatcherRule.testDispatcher)
        viewModel.getData().observeForever(observer)
    }

    @Test
     fun `test user data retrieved successfully`() = dispatcherRule.runBlockingTest{
        val username = "pranay1494"
        val data = User().apply { name = "Pranay" }

        Mockito.`when`(repository.fetchRepository(username)).thenReturn(data)

        viewModel.fetchUserData(username)

        Mockito.verify(observer,times(1)).onChanged(any())
    }
}