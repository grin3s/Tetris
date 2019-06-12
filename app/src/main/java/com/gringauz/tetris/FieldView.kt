package com.gringauz.tetris

import com.gringauz.tetris.core.TetrominoType

interface FieldView {
    fun setField(field: Array<TetrominoType?>)
    fun setScore(score: Int)
    fun setPauseButtonVisible(visible: Boolean)
    fun setResumeButtonVisible(visible: Boolean)
}
