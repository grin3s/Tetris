package com.gringauz.tetris.di

import android.content.Context
import android.os.Handler
import com.gringauz.tetris.core.EventLoop
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideEventLoop(@Named(value = "Application") context: Context): EventLoop {
        return object: EventLoop {
            val handler = Handler(context.mainLooper)
            override fun postDelayed(timeMillis: Long, task: () -> Unit) {
                handler.postDelayed(task, timeMillis)
            }
        }
    }

    @Provides
    @Named(value = "Application")
    fun provideApplicationContext(): Context {
        return context
    }
}
