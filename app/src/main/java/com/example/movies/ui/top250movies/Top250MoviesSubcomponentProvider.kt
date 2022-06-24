package com.example.movies.ui.top250movies

import com.example.movies.di.components.Top250MoviesSubcomponent

interface Top250MoviesSubcomponentProvider {

    fun initTop250MoviesSubcomponent(): Top250MoviesSubcomponent
    fun destroyTop250MoviesSubcomponent()
}