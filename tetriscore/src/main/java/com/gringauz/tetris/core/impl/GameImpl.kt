package com.gringauz.tetris.core.impl

import com.gringauz.subscription.ProviderImpl
import com.gringauz.tetris.core.*

private enum class Direction {
    LEFT,
    RIGHT,
    DOWN
}

private val SPAWN_POSITION = Pair(0, 3)

private fun move(direction: Direction, tetromino: Tetromino): Tetromino {
    val verticalMove = when (direction) {
        Direction.DOWN -> 1
        else -> 0
    }

    val horizontalMove = when (direction) {
        Direction.LEFT -> -1
        Direction.RIGHT -> 1
        else -> 0
    }

    return Tetromino(
        tetromino.type,
        Pair(
            tetromino.position.first + verticalMove,
            tetromino.position.second + horizontalMove),
        tetromino.rotationIndex)
}

private fun rotate(direction: Int, tetromino: Tetromino): Tetromino {
    var rot = (tetromino.rotationIndex + direction) % 4
    if (rot < 0) {
        rot += 4
    }
    return Tetromino(tetromino.type, tetromino.position, rot)
}

class GameImpl(private val eventLoop: EventLoop): Game, Gravity.Listener, ProviderImpl<Game.Listener>() {

    private val gravity: Gravity = GravityImpl(eventLoop)

    private var currentTetromino: Tetromino? = null

    private var fieldData: Array<TetrominoType?> = arrayOfNulls<TetrominoType?>(FIELD_HEIGHT * FIELD_WIDTH)

    private var score: Int = 0
        set(value) {
            field = value
            forEachListener { it.onScoreChanged() }
        }


    init {
        gravity.subscribe(this)
    }

    override fun onTick() {
        val newTetromino = move(Direction.DOWN, currentTetromino!!)
        val futureTetromino = move(Direction.DOWN, newTetromino)

        if (collides(futureTetromino)) {
            updateTetromino(newTetromino)
            var lineIdx = FIELD_HEIGHT - 1
            while (lineIdx >= 0) {
                var containsNull = false
                for (idx in lineIdx * FIELD_WIDTH until (lineIdx + 1) * FIELD_WIDTH) {
                    if (fieldData[idx] == null) {
                        containsNull = true
                        break
                    }
                }
                if (containsNull) {
                    lineIdx--
                } else {
                    for (idx in (lineIdx * FIELD_WIDTH - 1) downTo 0) {
                        fieldData[idx + FIELD_WIDTH] = fieldData[idx]
                    }
                    score++
                }
            }
            spawn()
        } else {
            updateTetromino(newTetromino)
        }
    }

    override fun onRightClick() {
        updateIfCan(move(Direction.RIGHT, currentTetromino!!))
    }

    override fun onLeftClick() {
        updateIfCan(move(Direction.LEFT, currentTetromino!!))
    }

    override fun onRightRotate() {
        updateIfCan(rotate(1, currentTetromino!!))
    }

    override fun onLeftRotate() {
        updateIfCan(rotate(-1, currentTetromino!!))
    }

    override fun onFastDropClick() {
        gravity.activate(GravityMode.FAST)
    }

    override fun onHoldPiece() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        score = 0
        fieldData.fill(null, 0, fieldData.size)
        spawn()
    }

    override fun pause() {
        gravity.deactivate()
    }

    override fun end() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun field(): Array<TetrominoType?> {
        return fieldData
    }

    override fun score(): Int {
        return score
    }

    private fun notifyFieldChange() {
        forEachListener { it.onFieldChanged() }
    }

    private fun spawn() {
        val newTetromino = Tetromino(
            type = TetrominoType.values().random(),
            position = SPAWN_POSITION,
            rotationIndex = 0)

        currentTetromino = null

        if (collides(newTetromino)) {
            forEachListener { it.onGameOver() }
            gravity.deactivate()
        } else {
            updateTetromino(newTetromino)
            gravity.activate(GravityMode.ORDINARY)
        }
    }

    private fun linearPos(position: Pair<Int, Int>) = position.first * FIELD_WIDTH + position.second

    private fun updateIfCan(newTetromino: Tetromino) {
        if (!collides(newTetromino)) {
            updateTetromino(newTetromino)
        }
    }

    private fun updateTetromino(newTetromino: Tetromino) {
        currentTetromino?.let { cur ->
            cur.positions().forEach { fieldData[linearPos(it)] = null }
        }

        newTetromino.positions().forEach { fieldData[linearPos(it)] = newTetromino.type }

        currentTetromino = newTetromino
        notifyFieldChange()
    }

    private fun collides(newTetromino: Tetromino): Boolean {
        newTetromino.positions().forEach { pos ->
            // check field boundaries
            if (pos.first < 0 || pos.first >= FIELD_HEIGHT) {
                return true
            }

            if (pos.second < 0 || pos.second >= FIELD_WIDTH) {
                return true
            }

            if (fieldData[linearPos(pos)] != null) {
                if (currentTetromino != null) {
                    if (!currentTetromino!!.positions().contains(pos)) {
                        return true
                    }
                } else {
                    return true
                }
            }
        }

        return false
    }
}
