package com.kannabi.simplelifecycleapilibrary.lifecycleapi.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ComponentStoreProvider

/**
 * An abstract class that incapsulate work with the components store
 *
 * */

abstract class ComponentHolderFragment<out M: Any>: Fragment() {
    private val COMPONENT_ID_KEY = "COMPONENT_ID_KEY"
    private lateinit var component: M
    private var componentId: Long = -1
    private lateinit var componentStore: ComponentStoreProvider

    protected var configurationChanging = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            component =
                (activity as ComponentStoreProvider).let {
                    (savedInstanceState?.getLong(COMPONENT_ID_KEY)
                        ?.let { id -> it.getComponent(id.also(::componentId::set)) }
                        ?: it.getComponent(
                            it.storeComponent(provideComponent()).also(::componentId::set)
                        )) as M
                }

        } catch (e: ClassCastException){
            e.printStackTrace()
            throw RuntimeException("${this::class.simpleName} " +
                    "should be used in activity that implements ${ComponentStoreProvider::class.simpleName}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(COMPONENT_ID_KEY, componentId)
        configurationChanging = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!configurationChanging) {
            componentStore.releaseComponent(componentId)
        }
    }

    abstract fun provideComponent(): M

    protected fun getComponent() = component
}