package com.example.movies.di.modules.scopes

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.domain.DataSourceRepository
import com.example.domain.GetTop250MoviesUseCase
import com.example.movies.di.annotations.Top250MoviesScope
import com.example.movies.di.annotations.ViewModelKey
import com.example.movies.ui.top250movies.Top250MoviesSubcomponentProvider
import com.example.movies.ui.top250movies.Top250MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface Top250MoviesModule {

    @Top250MoviesScope
    @Binds
    @IntoMap
    @ViewModelKey(Top250MoviesViewModel::class)
    fun bindTop250MoviesViewModel(vm: Top250MoviesViewModel): ViewModel

    companion object {

        @Top250MoviesScope
        @Provides
        fun provideTop250MoviesSubcomponentProvider(
            application: Application
        ): Top250MoviesSubcomponentProvider {
            return (application as Top250MoviesSubcomponentProvider)
        }

        @Top250MoviesScope
        @Provides
        fun provideGetTop250MoviesUseCase(
            dataSourceRepository: DataSourceRepository
        ): GetTop250MoviesUseCase {
            return GetTop250MoviesUseCase(dataSourceRepository)
        }
    }
}