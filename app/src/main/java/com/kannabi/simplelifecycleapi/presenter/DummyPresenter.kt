package com.kannabi.simplelifecycleapi.presenter

import com.kannabi.simplelifecycleapi.IDummyModel
import com.kannabi.simplelifecycleapi.IDummyPresenter
import com.kannabi.simplelifecycleapi.IDummyView

class DummyPresenter(private val model: IDummyModel): IDummyPresenter {
    override fun onViewReady() {
    }

    override fun attachView(view: IDummyView) {
    }

    override fun detachView() {
    }

    override fun onDestroy() {
    }
}