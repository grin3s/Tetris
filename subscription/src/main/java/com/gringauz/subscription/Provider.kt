package com.gringauz.subscription

interface Provider<T> {
    fun subscribe(listener: T)
    fun unsubscribe(listener: T)
}
