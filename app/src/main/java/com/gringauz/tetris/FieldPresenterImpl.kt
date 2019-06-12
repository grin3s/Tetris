package com.gringauz.tetris

import android.content.Context
import android.widget.Toast
import com.gringauz.tetris.core.Game

class FieldPresenterImpl (private val game: Game, private val context: Context): FieldPresenter, Game.Listener {

    lateinit var fieldView: FieldView

    init {
        game.subscribe(this)
    }

    override fun onFieldChanged() {
        fieldView.setField(game.field())
    }

    override fun onStartClick() {
        game.restart()
        fieldView.setResumeButtonVisible(false)
        fieldView.setPauseButtonVisible(true)
    }

    override fun onGameOver() {
        Toast.makeText(context, "Game over", Toast.LENGTH_LONG).show()
    }

    override fun onScoreChanged() {
        fieldView.setScore(game.score())
    }

    override fun setView(view: FieldView) {
        fieldView = view
        fieldView.setPauseButtonVisible(true)
        fieldView.setResumeButtonVisible(false)
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

    override fun onPauseClick() {
        game.pause()
        fieldView.setResumeButtonVisible(true)
        fieldView.setPauseButtonVisible(false)

    }

    override fun onResumeClick() {
        game.resume()
        fieldView.setResumeButtonVisible(false)
        fieldView.setPauseButtonVisible(true)
    }
}
