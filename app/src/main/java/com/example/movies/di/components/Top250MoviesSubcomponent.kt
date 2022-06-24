package com.example.movies.di.components

import com.example.movies.di.annotations.Top250MoviesScope
import com.example.movies.di.modules.scopes.Top250MoviesModule
import com.example.movies.ui.top250movies.Top250MoviesFragment
import dagger.Subcomponent

@Top250MoviesScope
@Subcomponent(modules = [Top250MoviesModule::class])
interface Top250MoviesSubcomponent {

    fun inject(fragment: Top250MoviesFragment)
}