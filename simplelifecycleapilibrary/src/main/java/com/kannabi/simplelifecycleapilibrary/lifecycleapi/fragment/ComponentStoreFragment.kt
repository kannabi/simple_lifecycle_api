package com.kannabi.simplelifecycleapilibrary.lifecycleapi.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ComponentStoreProvider

abstract class ComponentStoreFragment<out M: Any>: Fragment() {
    private val COMPONENT_ID_KEY = "COMPONENT_ID_KEY"
    private lateinit var component: M
    private var componentId: Long = -1
    private lateinit var componentStore: ComponentStoreProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            componentStore = (activity as ComponentStoreProvider)
            component = componentStore.getComponent(
                    savedInstanceState?.getLong(COMPONENT_ID_KEY) ?:
                    componentStore.storeComponent(provideComponent())
                            .also(::componentId::set)
            ) as M
        } catch (e: ClassCastException){
            throw RuntimeException("${this::class.simpleName} " +
                    "should be used in activity that implements ${ComponentStoreProvider::class.simpleName}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(COMPONENT_ID_KEY, componentId)
    }

    override fun onDestroy() {
        super.onDestroy()
        componentStore.releaseComponent(componentId)
    }

    abstract fun provideComponent(): M

    protected fun getComponent() = component
}