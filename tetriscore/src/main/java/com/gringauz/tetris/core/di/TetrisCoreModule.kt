package com.gringauz.tetris.core.di

import com.gringauz.tetris.core.EventLoop
import com.gringauz.tetris.core.Game
import com.gringauz.tetris.core.impl.GameImpl
import dagger.Module
import dagger.Provides

@Module
class TetrisCoreModule {
    @Provides fun provideGame(eventLoop: EventLoop): Game {
        return GameImpl(eventLoop)
    }
}
