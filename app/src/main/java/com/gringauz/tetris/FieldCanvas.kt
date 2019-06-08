package com.gringauz.tetris

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.annotation.ColorRes
import android.support.v4.content.res.ResourcesCompat
import android.util.AttributeSet
import android.view.View
import com.gringauz.tetris.core.FIELD_HEIGHT
import com.gringauz.tetris.core.FIELD_WIDTH
import com.gringauz.tetris.core.TetrominoType

class FieldCanvas: View {

    var field: Array<TetrominoType?> = emptyArray()
        set(value) {
            field = value
            invalidate()
        }

    private val paint = Paint().also {
        it.style = Paint.Style.FILL
    }

    private val cellWidthPx = context.resources.getDimensionPixelSize(R.dimen.width_field) / FIELD_WIDTH
    private val cellHeightPx = context.resources.getDimensionPixelSize(R.dimen.height_field) / FIELD_HEIGHT

    private val colors = hashMapOf(
        Pair(TetrominoType.I, ResourcesCompat.getColor(context.resources, R.color.color_tetromino_i, null)),
        Pair(TetrominoType.L, ResourcesCompat.getColor(context.resources, R.color.color_tetromino_l, null)),
        Pair(TetrominoType.J, ResourcesCompat.getColor(context.resources, R.color.color_tetromino_j, null)),
        Pair(TetrominoType.O, ResourcesCompat.getColor(context.resources, R.color.color_tetromino_o, null)),
        Pair(TetrominoType.S, ResourcesCompat.getColor(context.resources, R.color.color_tetromino_s, null)),
        Pair(TetrominoType.T, ResourcesCompat.getColor(context.resources, R.color.color_tetromino_t, null)),
        Pair(TetrominoType.Z, ResourcesCompat.getColor(context.resources, R.color.color_tetromino_z, null))

    )

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        field.forEachIndexed { index, tetrominoType ->
            val i = index / FIELD_WIDTH
            val j = index - i * FIELD_WIDTH
            tetrominoType?.let { squareType ->
                paint.color = colors[squareType]!!
                canvas!!.drawRect(
                    (j * cellWidthPx).toFloat(),
                    (i * cellHeightPx).toFloat(),
                    ((j + 1) * cellWidthPx).toFloat(),
                    ((i + 1) * cellHeightPx).toFloat(),
                    paint
                )
            }
        }
    }
}
