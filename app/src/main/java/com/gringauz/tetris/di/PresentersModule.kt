package com.gringauz.tetris.di

import android.content.Context
import com.gringauz.tetris.FieldPresenter
import com.gringauz.tetris.FieldPresenterImpl
import com.gringauz.tetris.core.Game
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class PresentersModule {
    @Provides
    fun provideFieldPresenter(game: Game, @Named(value = "Application") context: Context): FieldPresenter {
        return FieldPresenterImpl(game, context)
    }
}
