package com.example.coroutinesdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun layoutId() : Int

    private lateinit var viewDataBinding: ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater,layoutId(),container,false)
        viewDataBinding.setLifecycleOwner(this)
        return viewDataBinding.root
    }

    protected fun getViewBinding() = viewDataBinding
}