package com.gringauz.tetris.core.impl

import com.gringauz.tetris.core.*
import kotlin.random.Random

private enum class Direction {
    LEFT,
    RIGHT,
    DOWN
}

private val SPAWN_POSITION = Pair(0, 3)

class GameImpl(private val eventLoop: EventLoop): Game, Gravity.Listener {

    private val gravity: Gravity = GravityImpl(eventLoop)

    private lateinit var currentTetromino: Tetromino

    private val random = Random(42)

    private var listeners: HashSet<Game.Listener> = hashSetOf()

    private var fieldData: Array<TetrominoType?> = arrayOfNulls<TetrominoType?>(FIELD_HEIGHT * FIELD_WIDTH)

    init {
        gravity.subscribe(this)
    }

    override fun onTick() {
        val newTetromino = move(Direction.DOWN)
        if (collides(newTetromino)) {
            gravity.deactivate()
            eventLoop.postDelayed(500) { spawn() }
        } else {
            updateTetromino(newTetromino)
        }
    }

    override fun onRightClick() {
        updateIfCan(move(Direction.RIGHT))
    }

    override fun onLeftClick() {
        updateIfCan(move(Direction.LEFT))
    }

    override fun onRightRotate() {
        updateIfCan(rotate(1))
    }

    override fun onLeftRotate() {
        updateIfCan(rotate(-1))
    }

    override fun onFastDropClick() {
        gravity.activate(GravityMode.FAST)
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

    private fun notifyFieldChange() {
        listeners.forEach { it.onFieldChanged() }
    }

    private fun rotate(direction: Int): Tetromino {
        var rot = (currentTetromino.rotationIndex + direction) % 4
        if (rot < 0) {
            rot += 4
        }
        return Tetromino(currentTetromino.type, currentTetromino.position, rot)
    }

    private fun move(direction: Direction): Tetromino {
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
            currentTetromino.type,
            Pair(
                currentTetromino.position.first + verticalMove,
                currentTetromino.position.second + horizontalMove),
            currentTetromino.rotationIndex)
    }

    private fun spawn() {
        currentTetromino = Tetromino(
            type = TetrominoType.values().random(random),
            position = SPAWN_POSITION,
            rotationIndex = 0)

        updateTetromino(currentTetromino)

        gravity.activate(GravityMode.ORDINARY)
    }

    private fun linearPos(position: Pair<Int, Int>) = position.first * FIELD_WIDTH + position.second

    private fun updateIfCan(newTetromino: Tetromino) {
        if (!collides(newTetromino)) {
            updateTetromino(newTetromino)
        }
    }

    private fun updateTetromino(newTetromino: Tetromino) {
        currentTetromino.positions().forEach { fieldData[linearPos(it)] = null }
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

            // check other stuff
            if (!currentTetromino.positions().contains(pos)) {
                if (fieldData[linearPos(pos)] != null) {
                    return true
                }
            }
        }

        return false
    }
}
