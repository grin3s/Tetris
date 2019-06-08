package com.gringauz.tetris.core

import com.gringauz.subscription.Provider

interface Gravity: Provider<Gravity.Listener> {
    interface Listener {
        fun onTick()
    }

    fun activate()
    fun deactivate()
}
