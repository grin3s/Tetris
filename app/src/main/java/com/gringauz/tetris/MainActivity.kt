package com.gringauz.tetris

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.gringauz.tetris.core.EventLoop
import com.gringauz.tetris.core.Game
import com.gringauz.tetris.core.impl.GameImpl
import com.gringauz.tetris.core.impl.GravityImpl
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
