package com.gringauz.tetris

import android.app.Application
import com.gringauz.tetris.di.AppComponent
import com.gringauz.tetris.di.AppModule
import com.gringauz.tetris.di.DaggerAppComponent

class TetrisApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
