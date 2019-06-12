package com.gringauz.tetris.core

import com.gringauz.subscription.Provider

const val FIELD_WIDTH = 10
const val FIELD_HEIGHT = 20

interface Game: Provider<Game.Listener> {
    interface Listener {
        fun onFieldChanged()
        fun onGameOver()
        fun onScoreChanged()
    }

    fun restart()

    fun pause()
    fun resume()

    fun onRightClick()
    fun onLeftClick()

    fun onRightRotate()
    fun onLeftRotate()

    fun onFastDropClick()

    fun onHoldPiece()

    fun field(): Array<TetrominoType?>

    fun score(): Int
}
