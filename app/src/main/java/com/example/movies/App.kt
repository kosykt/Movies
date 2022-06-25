package com.example.movies

import android.app.Application
import com.example.movies.di.components.AppComponent
import com.example.movies.di.components.DaggerAppComponent
import com.example.movies.di.components.DetailsSubcomponent
import com.example.movies.di.components.Top250MoviesSubcomponent
import com.example.movies.di.modules.singletones.AppModule
import com.example.movies.ui.details.DetailsSubcomponentProvider
import com.example.movies.ui.top250movies.Top250MoviesSubcomponentProvider

class App : Application(), Top250MoviesSubcomponentProvider, DetailsSubcomponentProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    private var top250MoviesSubcomponent: Top250MoviesSubcomponent? = null
    private var detailsSubcomponent: DetailsSubcomponent? = null

    override fun initTop250MoviesSubcomponent() = appComponent
        .provideTop250MoviesSubcomponent()
        .also {
            if (top250MoviesSubcomponent == null) {
                top250MoviesSubcomponent = it
            }
        }

    override fun destroyTop250MoviesSubcomponent() {
        top250MoviesSubcomponent = null
    }

    override fun initDetailsSubcomponent() = appComponent
        .provideDetailsSubcomponent()
        .also {
            if (detailsSubcomponent == null) {
                detailsSubcomponent = it
            }
        }

    override fun destroyDetailsSubcomponent() {
        detailsSubcomponent = null
    }
}