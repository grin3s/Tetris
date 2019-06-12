package com.gringauz.tetris.di

import com.gringauz.tetris.MainActivity
import com.gringauz.tetris.core.di.TetrisCoreModule
import dagger.Component

@Component(modules = [TetrisCoreModule::class, AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
