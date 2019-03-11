package com.example.coroutinesdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.coroutinesdemo.R
import com.example.coroutinesdemo.base.BaseFragment
import com.example.coroutinesdemo.base.BaseViewModel
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserFragment : BaseFragment(){

    override fun getViewModel(): BaseViewModel = mViewModel

    override fun layoutId() = R.layout.activity_user

    private lateinit var mViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel =  ViewModelProviders.of(this,mViewModelFactory).get(UserViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        fetchData()
    }

    fun fetchData() = launch(Dispatchers.IO){
        Log.d("current_thread",Thread.currentThread().name)
        mViewModel.fetchUserData("pranay1494")
        withContext(Dispatchers.Main){
            Log.d("current_thread",Thread.currentThread().name)
            textView.setText("Done")
        }
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}
