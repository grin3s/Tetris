package com.gringauz.tetris

import com.gringauz.tetris.core.TetrominoType

interface FieldView {
    fun setField(field: Array<TetrominoType?>)
}
