package com.kannabi.simplelifecycleapilibrary.lifecycleapi.activity

import android.os.Bundle
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ComponentStoreProvider

abstract class ComponentHolderActivity<out M: Any>: ComponentStoreActivity() {
    private val COMPONENT_ID_KEY = "COMPONENT_ID_KEY"
    private lateinit var component: M
    private var componentId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            component =
                (savedInstanceState?.getLong(COMPONENT_ID_KEY)
                    ?.let { getComponent(it.also(::componentId::set)) }
                    ?: getComponent(
                        storeComponent(provideComponent()).also(::componentId::set)
                    )) as M
        } catch (e: ClassCastException) {
            e.printStackTrace()
            throw RuntimeException("${this::class.simpleName} " +
                    "should be inherit an activity that implements ${ComponentStoreProvider::class.simpleName}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(COMPONENT_ID_KEY, componentId)
    }

    override fun onDestroy() {
        if (isFinishing) {
            releaseComponent(componentId)
        }
        super.onDestroy()
    }

    abstract fun provideComponent(): M

    protected fun getComponent() = component
}