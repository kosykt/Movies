package com.example.movies.di.modules.singletones

import android.app.Application
import com.example.data.network.NetworkRepositoryImpl
import com.example.data.network.RetrofitService
import com.example.data.repository.DataSourceRepositoryImpl
import com.example.data.repository.NetworkRepository
import com.example.domain.DataSourceRepository
import com.example.movies.utils.NetworkObserver
import com.example.movies.utils.imageloader.AppImageLoader
import com.example.movies.utils.imageloader.CoilImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideNetworkObserver(): NetworkObserver {
        return NetworkObserver(application)
    }

    @Singleton
    @Provides
    fun provideDataSourceRepository(
        network: NetworkRepository
    ): DataSourceRepository {
        return DataSourceRepositoryImpl(network)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(retrofitService: RetrofitService): NetworkRepository {
        return NetworkRepositoryImpl(retrofitService)
    }

    @Singleton
    @Provides
    fun provideAppImageLoader(): AppImageLoader {
        return CoilImageLoader()
    }
}