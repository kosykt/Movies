package com.example.movies.di.components

import com.example.movies.di.annotations.DetailsScope
import com.example.movies.di.modules.scopes.DetailsModule
import com.example.movies.ui.details.DetailsFragment
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [DetailsModule::class])
interface DetailsSubcomponent {

    fun inject(fragment: DetailsFragment)
}