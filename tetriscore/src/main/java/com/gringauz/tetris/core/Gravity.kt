package com.gringauz.tetris.core

import com.gringauz.subscription.Provider

enum class GravityMode {
    ORDINARY,
    FAST
}

interface Gravity: Provider<Gravity.Listener> {
    interface Listener {
        fun onTick()
    }

    fun activate(mode: GravityMode)
    fun deactivate()
}
