package com.kannabi.simplelifecycleapilibrary.lifecycleapi.activity

import android.os.Bundle
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.Presenter
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ProvidePresenter

/**
 * An abstract class which incapsulate a work with the presenter
 * and provide it by the {@link #getPresenter} method.
 * */

abstract class PresenterActivity<in V, out M: ProvidePresenter<P>, P: Presenter<V>>: ComponentHolderActivity<M>(){

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
        if (isFinishing) {
            mPresenter.onDestroy()
        }
    }

    protected fun getPresenter() = mPresenter
}