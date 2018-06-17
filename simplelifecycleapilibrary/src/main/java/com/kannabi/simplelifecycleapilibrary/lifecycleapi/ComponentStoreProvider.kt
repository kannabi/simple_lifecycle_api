package com.kannabi.simplelifecycleapilibrary.lifecycleapi

interface ComponentStoreProvider {
    fun storeComponent(component: Any): Long

    fun getComponent(componentId: Long): Any?

    fun releaseComponent(componentId: Long)

    fun isStored(componentId: Long): Boolean
}