package com.kannabi.simplelifecycleapilibrary.lifecycleapi

/**
 * A interface for describing a class which store components
 *
 * */

interface ComponentStoreProvider {

    /**
     * A method store the component and return id that associated with it
     * @param component to store
     * @return associated id
     * */
    fun storeComponent(component: Any): Long

    /**
     * Return component associated with componentId
     * @param componentId id of the component that returned in {@link #storeComponent}
     * @return stored component
     * */
    fun getComponent(componentId: Long): Any?

    /**
     * Delete component by id
     * @param componentId id of the component
     */
    fun releaseComponent(componentId: Long)

    /**
     * Check that there is component with an associated id
     * @param componentId id of the component
     * @return true if there is component by componentId
     * */
    fun isStored(componentId: Long): Boolean
}