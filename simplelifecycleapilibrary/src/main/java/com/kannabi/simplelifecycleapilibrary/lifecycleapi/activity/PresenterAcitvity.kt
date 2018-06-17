package com.kannabi.simplelifecycleapilibrary.lifecycleapi.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.awsm_guys.mobileclicker.lifecycleapi.Presenter
import com.awsm_guys.mobileclicker.lifecycleapi.ProvidePresenter

abstract class PresenterActivity<in V, out M: ProvidePresenter<P>, P: Presenter<V>>: ComponentStoreActivity<M>(){

    private lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = getComponent().getPresenter()
    }

    override fun onStart() {
        super.onStart()
        getPresenter().attachView(this as V)
    }

    override fun onResume() {
        super.onResume()
        getPresenter().onViewReady()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    protected fun getPresenter() = mPresenter
}