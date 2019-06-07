package com.gringauz.tetris

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gringauz.tetris.core.impl.GameImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val game = GameImpl()
        (view_field as FieldViewImpl).presenter = FieldPresenterImpl(game)
        game.start()
    }
}
