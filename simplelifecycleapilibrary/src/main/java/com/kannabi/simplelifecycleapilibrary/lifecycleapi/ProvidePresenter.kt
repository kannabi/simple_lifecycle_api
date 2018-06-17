package com.kannabi.simplelifecycleapilibrary.lifecycleapi

/**
 * This interface should be inherit by the component that you want to use
 * in the {@link PresenterActivity} or {@link PresenterFragment} class.
 * */
interface ProvidePresenter <P> {
    fun getPresenter(): P
}