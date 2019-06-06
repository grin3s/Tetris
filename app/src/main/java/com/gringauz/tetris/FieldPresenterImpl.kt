package com.gringauz.tetris

import com.gringauz.tetris.core.Game

class FieldPresenterImpl(private val game: Game): FieldPresenter {
    lateinit var fieldView: FieldView

    override fun setView(view: FieldView) {
        fieldView = view
        fieldView.setField(game.field())
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHoldPiece() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
