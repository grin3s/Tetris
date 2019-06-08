package com.gringauz.subscription

abstract class ProviderImpl<T>: Provider<T> {
    private val listeners: HashSet<T> = hashSetOf()

    fun forEachListener(action: (T) -> Unit) {
        listeners.forEach { action(it) }
    }

    override fun subscribe(listener: T) {
        listeners.add(listener)
    }

    override fun unsubscribe(listener: T) {
        listeners.remove(listener)
    }
}
