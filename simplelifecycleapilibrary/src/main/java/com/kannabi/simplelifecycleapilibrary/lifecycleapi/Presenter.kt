package com.awsm_guys.mobileclicker.lifecycleapi

/**
 * Created by hekpo on 04.02.2018.
 */
interface Presenter<in V> {
    fun attachView(view: V)

    fun onViewReady()

    fun detachView()

    fun onDestroy()
}