package com.gringauz.tetris.core.impl

import com.gringauz.subscription.ProviderImpl
import com.gringauz.tetris.core.EventLoop
import com.gringauz.tetris.core.Gravity


class GravityImpl(private val eventLoop: EventLoop):
    Gravity,
    ProviderImpl<Gravity.Listener>()
{
    private var handle: Handle? = null

    override fun activate() {
        handle?.cancel()
        handle = Handle().also { it.start() }
    }

    override fun deactivate() {
        handle?.cancel()
    }

    private inner class Handle {
        private var cancelled: Boolean = false

        fun start() {
            eventLoop.postDelayed(1000) {
                if (!cancelled) {
                    forEachListener { it.onTick() }
                    start()
                }
            }
        }

        fun cancel() {
            cancelled = true
        }
    }
}
