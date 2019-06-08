package com.gringauz.tetris

import com.gringauz.tetris.core.Game

class FieldPresenterImpl(private val game: Game): FieldPresenter, Game.Listener {

    lateinit var fieldView: FieldView

    init {
        game.subscribe(this)
    }

    override fun onFieldChanged() {
        fieldView.setField(game.field())
    }

    override fun onStartClick() {
        game.start()
    }

    override fun onGameOver() {

    }

    override fun onScoreChanged() {

    }

    override fun setView(view: FieldView) {
        fieldView = view
    }

    override fun onRightClick() {
        game.onRightClick()
    }

    override fun onLeftClick() {
        game.onLeftClick()
    }

    override fun onRightRotate() {
        game.onRightRotate()
    }

    override fun onLeftRotate() {
        game.onLeftRotate()
    }

    override fun onFastDropClick() {
        game.onFastDropClick()
    }

    override fun onHoldPiece() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
