package com.kannabi.simplelifecycleapilibrary.lifecycleapi.fragment

import android.os.Bundle
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.Presenter
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ProvidePresenter

/**
 * An abstract class which incapsulate a work with the presenter
 * and provide it by the {@link #getPresenter} method.
 * */

abstract class PresenterFragment<in V, out M: ProvidePresenter<P>, P: Presenter<V>>:
                                                        ComponentHolderFragment<M>() {
    private lateinit var mPresenter: P

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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