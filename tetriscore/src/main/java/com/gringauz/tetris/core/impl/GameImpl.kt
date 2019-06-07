package com.gringauz.tetris.core.impl

import com.gringauz.tetris.core.*

class GameImpl: Game {
    private lateinit var currentTetromino: Tetromino

    private var listeners: HashSet<Game.Listener> = hashSetOf()

    private var fieldData: Array<TetrominoType?> = arrayOfNulls<TetrominoType?>(FIELD_HEIGHT * FIELD_WIDTH)

    override fun onRightClick() {
        shift(1)
    }

    override fun onLeftClick() {
        shift(-1)
    }

    override fun onRightRotate() {
        rotate(1)
    }

    override fun onLeftRotate() {
        rotate(-1)
    }

    override fun onFastDropClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onHoldPiece() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe(listener: Game.Listener) {
        listeners.add(listener)
    }

    override fun unsubscribe(listener: Game.Listener) {
        listeners.remove(listener)
    }

    override fun start() {
        currentTetromino = Tetromino(
            type = TetrominoType.I,
            position = Pair(0, 0),
            rotationIndex = 0)

        notifyFieldChange()
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

    private fun notifyFieldChange() {
        listeners.forEach { it.onFieldChanged() }
    }

    private fun rotate(direction: Int) {
        var rot = (currentTetromino.rotationIndex + direction) % 4
        if (rot < 0) {
            rot += 4
        }
        val newTetromino = Tetromino(currentTetromino.type, currentTetromino.position, rot)
        updateTetromino(newTetromino)
    }

    private fun shift(direction: Int) {
        val newTetromino = Tetromino(currentTetromino.type, Pair(currentTetromino.position.first, currentTetromino.position.second  + direction), currentTetromino.rotationIndex)
        updateTetromino(newTetromino)
    }

    private fun updateTetromino(newTetromino: Tetromino) {
        val curPos = currentTetromino.position
        currentTetromino.type.states[currentTetromino.rotationIndex].forEach {
            fieldData[(curPos.first + it.first) * FIELD_WIDTH + curPos.second + it.second] = null
        }

        val newPos = newTetromino.position
        newTetromino.type.states[newTetromino.rotationIndex].forEach {
            fieldData[(newPos.first + it.first) * FIELD_WIDTH + newPos.second + it.second] = newTetromino.type
        }

        currentTetromino = newTetromino
        notifyFieldChange()
    }
}
