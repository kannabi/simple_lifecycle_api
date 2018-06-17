package com.kannabi.simplelifecycleapilibrary.lifecycleapi

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class ComponentStore: ComponentStoreProvider {

    private val componentMap: ConcurrentHashMap<Long, Any> = ConcurrentHashMap()
    private val componentIdCounter: AtomicLong = AtomicLong()

    override fun getComponent(componentId: Long) =
            componentMap[componentId]

    override fun releaseComponent(componentId: Long) {
        componentMap.remove(componentId)
    }

    override fun isStored(componentId: Long) =
            componentMap.contains(componentId)

    override fun storeComponent(component: Any): Long =
        componentIdCounter.incrementAndGet().apply {
            componentMap.put(this, component)
        }

    fun flushComponents(){
        val i = componentMap.iterator()
        while (i.hasNext()){
            componentMap.remove(i.next().key)
        }
    }
}