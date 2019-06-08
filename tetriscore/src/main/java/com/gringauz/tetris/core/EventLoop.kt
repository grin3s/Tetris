package com.gringauz.tetris.core

interface EventLoop {
    fun postDelayed(timeMillis: Long, task: () -> Unit)
}
