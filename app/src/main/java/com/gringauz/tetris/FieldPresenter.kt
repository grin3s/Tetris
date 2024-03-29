package com.gringauz.tetris

interface FieldPresenter {
    fun onRightClick()
    fun onLeftClick()

    fun onRightRotate()
    fun onLeftRotate()

    fun onFastDropClick()

    fun onHoldPiece()

    fun onStartClick()

    fun onPauseClick()
    fun onResumeClick()

    fun setView(view: FieldView)
}
