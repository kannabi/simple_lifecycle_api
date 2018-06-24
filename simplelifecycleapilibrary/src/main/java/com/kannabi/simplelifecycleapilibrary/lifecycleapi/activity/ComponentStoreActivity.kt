package com.kannabi.simplelifecycleapilibrary.lifecycleapi.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ComponentStore
import com.kannabi.simplelifecycleapilibrary.lifecycleapi.ComponentStoreProvider

/**
 * An abstract class that incapsulate work with the components store
 *
 * */


abstract class ComponentStoreActivity: AppCompatActivity(), ComponentStoreProvider {
    private lateinit var componentStore: ComponentStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentStore = lastCustomNonConfigurationInstance as? ComponentStore ?: ComponentStore()
    }

    override fun onRetainCustomNonConfigurationInstance() = componentStore

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing){
            componentStore.flushComponents()
        }
    }

    override fun storeComponent(component: Any) = componentStore.storeComponent(component)

    override fun getComponent(componentId: Long) = componentStore.getComponent(componentId)

    override fun releaseComponent(componentId: Long) = componentStore.releaseComponent(componentId)

    override fun isStored(componentId: Long) = componentStore.isStored(componentId)
}