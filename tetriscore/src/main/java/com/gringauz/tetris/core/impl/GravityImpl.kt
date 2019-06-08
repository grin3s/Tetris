package com.gringauz.tetris.core.impl

import com.gringauz.subscription.ProviderImpl
import com.gringauz.tetris.core.EventLoop
import com.gringauz.tetris.core.Gravity
import com.gringauz.tetris.core.GravityMode

private fun delay(mode: GravityMode): Long {
    return when (mode) {
        GravityMode.ORDINARY -> 1000
        GravityMode.FAST -> 100
    }
}

class GravityImpl(private val eventLoop: EventLoop):
    Gravity,
    ProviderImpl<Gravity.Listener>()
{
    private var handle: Handle? = null

    override fun activate(mode: GravityMode) {
        handle?.cancel()
        handle = Handle(mode).also { it.start() }
    }

    override fun deactivate() {
        handle?.cancel()
    }

    private inner class Handle(private val mode: GravityMode) {
        private var cancelled: Boolean = false

        fun start() {
            eventLoop.postDelayed(delay(mode)) {
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
