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
            arrayOf(Pair(0, 0), Pair(1, 0), Pair(1, 1), Pair(1, 2))
        )
    },
    J {
        override val states = arrayOf(arrayOf(Pair(1,2)))
    },
    O {
        override val states = arrayOf(arrayOf(Pair(1,2)))
    },
    S {
        override val states = arrayOf(arrayOf(Pair(1,2)))
    },
    T {
        override val states = arrayOf(arrayOf(Pair(1,2)))
    },
    Z {
        override val states = arrayOf(arrayOf(Pair(1,2)))
    };

    abstract val states: Array<Array<Pair<Int, Int>>>
}

class Tetromino(
    val type: TetrominoType,
    var position: Pair<Int, Int>,
    var rotationIndex: Int)
