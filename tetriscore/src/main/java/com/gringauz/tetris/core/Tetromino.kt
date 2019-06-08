package com.gringauz.tetris.core

enum class TetrominoType {
    I {
        override val states = arrayOf(
            arrayOf(Pair(1, 0), Pair(1, 1), Pair(1, 2), Pair(1, 3)),
            arrayOf(Pair(0, 2), Pair(1, 2), Pair(2, 2), Pair(3, 2)),
            arrayOf(Pair(2, 0), Pair(2, 1), Pair(2, 2), Pair(2, 3)),
            arrayOf(Pair(0, 1), Pair(1, 1), Pair(2, 1), Pair(3, 1))
        )
    },
    L {
        override val states = arrayOf(
            arrayOf(Pair(0, 0), Pair(1, 0), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(0, 2), Pair(0, 1), Pair(1, 1), Pair(2, 1)),
            arrayOf(Pair(2, 2), Pair(1, 2), Pair(1, 1), Pair(1, 0)),
            arrayOf(Pair(2, 0), Pair(2, 1), Pair(1, 1), Pair(0, 1))
        )
    },
    J {
        override val states = arrayOf(
            arrayOf(Pair(0, 2), Pair(1, 2), Pair(1, 1), Pair(1, 0)),
            arrayOf(Pair(2, 2), Pair(2, 1), Pair(1, 1), Pair(0, 1)),
            arrayOf(Pair(2, 0), Pair(1, 0), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(0, 0), Pair(0, 1), Pair(1, 1), Pair(2, 1))
        )
    },
    O {
        override val states = arrayOf(
            arrayOf(Pair(0, 1), Pair(0, 2), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(0, 1), Pair(0, 2), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(0, 1), Pair(0, 2), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(0, 1), Pair(0, 2), Pair(1, 1), Pair(1, 2))
        )
    },
    S {
        override val states = arrayOf(
            arrayOf(Pair(1, 0), Pair(1, 1), Pair(0, 1), Pair(0, 2)),
            arrayOf(Pair(0, 1), Pair(1, 1), Pair(1, 2), Pair(2, 2)),
            arrayOf(Pair(2, 0), Pair(2, 1), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(0, 0), Pair(1, 0), Pair(1, 1), Pair(2, 1))
        )
    },
    T {
        override val states = arrayOf(
            arrayOf(Pair(1, 0), Pair(1, 1), Pair(0, 1), Pair(1, 2)),
            arrayOf(Pair(0, 1), Pair(1, 1), Pair(2, 1), Pair(1, 2)),
            arrayOf(Pair(1, 0), Pair(1, 1), Pair(1, 2), Pair(2, 1)),
            arrayOf(Pair(1, 0), Pair(1, 1), Pair(0, 1), Pair(2, 1))
        )
    },
    Z {
        override val states = arrayOf(
            arrayOf(Pair(0, 0), Pair(0, 1), Pair(1, 1), Pair(1, 2)),
            arrayOf(Pair(0, 2), Pair(1, 2), Pair(1, 1), Pair(2, 1)),
            arrayOf(Pair(1, 0), Pair(1, 1), Pair(2, 1), Pair(2, 2)),
            arrayOf(Pair(0, 1), Pair(1, 1), Pair(1, 0), Pair(2, 0))
        )
    };

    abstract val states: Array<Array<Pair<Int, Int>>>
}

class Tetromino(
    val type: TetrominoType,
    var position: Pair<Int, Int>,
    var rotationIndex: Int) {

    fun state() = type.states[rotationIndex]
    fun positions() = state().map { Pair(position.first + it.first, position.second + it.second) }
}
