package com.example.coroutinesdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutinesdemo.base.BaseActivity

class UserActivity : BaseActivity(){
    override fun fragment(): BaseFragment = UserFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}