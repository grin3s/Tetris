package com.gringauz.tetris.core

val FIELD_WIDTH = 10
val FIELD_HEIGHT = 20

interface Game {
    interface Listener {
        fun onFieldChanged()
        fun onGameOver()
        fun onScoreChanged()
    }

    fun subscribe(listener: Listener)
    fun unsubscribe(listener: Listener)

    fun start()
    fun pause()
    fun end()

    fun onRightClick()
    fun onLeftClick()

    fun onRightRotate()
    fun onLeftRotate()

    fun onFastDropClick()

    fun onHoldPiece()

    fun field(): Array<TetrominoType?>
}
