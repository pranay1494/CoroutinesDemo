package com.example.coroutinesdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coroutinesdemo.R
import com.example.coroutinesdemo.utils.inTransaction
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        replaceFragment(savedInstanceState)
    }

    private fun replaceFragment(savedInstanceState: Bundle?) =
        savedInstanceState
            ?: supportFragmentManager.inTransaction { replace(R.id.blank_frame, fragment()) }

    abstract fun fragment(): BaseFragment
}