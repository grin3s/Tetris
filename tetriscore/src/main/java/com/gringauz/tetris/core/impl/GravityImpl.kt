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
    private lateinit var mode: GravityMode

    override fun setMode(mode: GravityMode) {
        this.mode = mode
        activate()
    }

    override fun activate() {
        handle?.cancel()
        handle = Handle().apply { start() }
    }

    override fun deactivate() {
        handle?.cancel()
        handle = null
    }

    private inner class Handle {
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
