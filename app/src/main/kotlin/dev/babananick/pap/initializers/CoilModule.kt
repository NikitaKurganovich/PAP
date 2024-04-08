package dev.babananick.pap.initializers

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.memory.MemoryCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {
    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context).maxSizePercent(0.25).build()
            }
            .crossfade(true)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
}