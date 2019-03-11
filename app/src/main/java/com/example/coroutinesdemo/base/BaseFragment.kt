package com.example.coroutinesdemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinesdemo.R
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    private lateinit var viewDataBinding: ViewDataBinding

    @Inject
    lateinit var mViewModelFactory : ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_base,container,false)
        val baseContainer = view.findViewById<FrameLayout>(R.id.frameContent)
        baseContainer.addView(inflater.inflate(layoutId(),container,false))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().viewStatus.observe(this, Observer{
            when(it){
                ViewStatus.LOADING -> Toast.makeText(context,"Loading...",Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show()
            }
        })
    }

    protected fun getViewBinding() = viewDataBinding
    abstract fun getViewModel() : BaseViewModel
    abstract fun layoutId() : Int
}