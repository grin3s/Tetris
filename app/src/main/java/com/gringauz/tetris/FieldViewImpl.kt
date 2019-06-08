package com.gringauz.tetris

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.gringauz.tetris.core.TetrominoType
import kotlinx.android.synthetic.main.layout_field.view.*

class FieldViewImpl: FieldView, ConstraintLayout {

    var presenter: FieldPresenter? = null
        set(value) {
            field = value
            field!!.setView(this)
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setField(field: Array<TetrominoType?>) {
        view_field_canvas.field = field
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        bt_rotate_left.setOnClickListener { presenter!!.onLeftRotate() }
        bt_rotate_right.setOnClickListener { presenter!!.onRightRotate() }
        bt_shift_left.setOnClickListener { presenter!!.onLeftClick() }
        bt_shift_right.setOnClickListener { presenter!!.onRightClick() }
        bt_start.setOnClickListener { presenter!!.onStartClick() }
    }
}

