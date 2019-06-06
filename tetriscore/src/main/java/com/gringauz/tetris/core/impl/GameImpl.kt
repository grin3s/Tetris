package com.gringauz.tetris.core.impl

import com.gringauz.tetris.core.FIELD_HEIGHT
import com.gringauz.tetris.core.FIELD_WIDTH
import com.gringauz.tetris.core.Game
import com.gringauz.tetris.core.TetrominoType

class GameImpl: Game {
    private var fieldData: Array<TetrominoType?> = arrayOfNulls<TetrominoType?>(FIELD_HEIGHT * FIELD_WIDTH).also {
        it[10] = TetrominoType.J
    }

    override fun onRightClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLeftClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRightRotate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLeftRotate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFastDropClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHoldPiece() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe(listener: Game.Listener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe(listener: Game.Listener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pause() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun end() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun field(): Array<TetrominoType?> {
        return fieldData
    }
}
