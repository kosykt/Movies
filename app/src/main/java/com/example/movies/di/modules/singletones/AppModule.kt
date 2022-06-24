package com.example.movies.di.modules.singletones

import android.app.Application
import com.example.data.database.AppDatabase
import com.example.data.database.DatabaseDataSourceImpl
import com.example.data.network.NetworkDataSourceImpl
import com.example.data.network.RetrofitService
import com.example.data.repository.DataSourceRepositoryImpl
import com.example.data.repository.DatabaseDataSource
import com.example.data.repository.NetworkDataSource
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
    fun provideAppImageLoader(): AppImageLoader {
        return CoilImageLoader()
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(retrofitService: RetrofitService): NetworkDataSource {
        return NetworkDataSourceImpl(retrofitService)
    }

    @Singleton
    @Provides
    fun provideDatabaseDataSource(db: AppDatabase): DatabaseDataSource {
        return DatabaseDataSourceImpl(db)
    }

    @Singleton
    @Provides
    fun provideDataSourceRepository(
        networkDataSource: NetworkDataSource,
        databaseDataSource: DatabaseDataSource,
    ): DataSourceRepository {
        return DataSourceRepositoryImpl(networkDataSource, databaseDataSource)
    }
}