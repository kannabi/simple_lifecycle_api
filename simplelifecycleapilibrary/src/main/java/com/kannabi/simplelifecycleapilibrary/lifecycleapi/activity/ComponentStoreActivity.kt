package com.kannabi.simplelifecycleapilibrary.lifecycleapi.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ComponentStore
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ComponentStoreProvider

/**
 * An abstract class that incapsulate work with the components store
 *
 * */


abstract class ComponentStoreActivity<out M: Any>: AppCompatActivity(), ComponentStoreProvider {
    private val COMPONENT_ID_KEY = "COMPONENT_ID_KEY"

    private lateinit var currentComponent: M

    private lateinit var componentStore: ComponentStore
    private var componentId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentStore = lastNonConfigurationInstance as ComponentStore? ?: ComponentStore()
        currentComponent = componentStore.getComponent(
                savedInstanceState?.getLong(COMPONENT_ID_KEY) ?:
                                componentStore.storeComponent(provideComponent())
                                                        .also(::componentId::set)
        ) as M
    }



    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putLong(COMPONENT_ID_KEY, componentId)
    }

    override fun onRetainCustomNonConfigurationInstance() = componentStore

    override fun onDestroy() {
        super.onDestroy()
        componentStore.flushComponents()
    }

    protected abstract fun provideComponent(): M

    protected fun getComponent() = currentComponent


    override fun storeComponent(component: Any) = componentStore.storeComponent(component)

    override fun getComponent(componentId: Long) = componentStore.getComponent(componentId)

    override fun releaseComponent(componentId: Long) = componentStore.releaseComponent(componentId)

    override fun isStored(componentId: Long) = componentStore.isStored(componentId)
}