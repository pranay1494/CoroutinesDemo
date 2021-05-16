package com.example.coroutinesdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.coroutinesdemo.R
import com.example.coroutinesdemo.base.BaseFragment
import com.example.coroutinesdemo.base.BaseViewModel
import com.example.coroutinesdemo.utils.loadImage
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
        observeData()
        fetchData()
    }

    private fun observeData() {
        mViewModel.getData().observe(this, Observer {
            Log.d("current_thread",Thread.currentThread().name)
            textView.text = it.name
            imageView.loadImage(it.avatarUrl)
        })


        mViewModel.getLaunchData().observe(this, Observer {
            Log.d("pranay",it.toString())
            Toast.makeText(requireContext(),"called",Toast.LENGTH_SHORT).show()
        })
    }

    fun fetchData() {
        Log.d("current_thread",Thread.currentThread().name)
        mViewModel.fetchUserData("pranay1494")
    }

    companion object {
        fun newInstance() = UserFragment()
    }
}
