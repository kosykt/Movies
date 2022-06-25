package com.example.movies.ui.details

import com.example.movies.di.components.DetailsSubcomponent

interface DetailsSubcomponentProvider {

    fun initDetailsSubcomponent(): DetailsSubcomponent
    fun destroyDetailsSubcomponent()
}