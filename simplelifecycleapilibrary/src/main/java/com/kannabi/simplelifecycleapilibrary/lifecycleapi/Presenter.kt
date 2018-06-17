package com.kannabi.simplelifecycleapilibrary.lifecycleapi

/**
 * A interface of a really base presenter
 *
 * */
interface Presenter<in V> {
    fun attachView(view: V)

    fun onViewReady()

    fun detachView()

    fun onDestroy()
}