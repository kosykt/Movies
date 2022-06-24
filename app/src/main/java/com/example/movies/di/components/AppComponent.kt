package com.example.movies.di.components

import com.example.movies.di.modules.ViewModelModule
import com.example.movies.di.modules.singletones.AppModule
import com.example.movies.di.modules.singletones.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    fun provideTop250MoviesSubcomponent(): Top250MoviesSubcomponent
}