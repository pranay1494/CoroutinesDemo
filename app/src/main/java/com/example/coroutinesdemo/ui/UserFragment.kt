package com.example.coroutinesdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coroutinesdemo.R
import com.example.coroutinesdemo.base.BaseFragment

class UserFragment : BaseFragment(){
    override fun layoutId() = R.layout.activity_user

    companion object {
        fun newInstance() = UserFragment()
    }
}
