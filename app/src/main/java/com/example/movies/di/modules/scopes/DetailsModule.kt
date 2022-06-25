package com.example.movies.di.modules.scopes

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.domain.DataSourceRepository
import com.example.domain.GetDetailsUseCse
import com.example.movies.di.annotations.DetailsScope
import com.example.movies.di.annotations.ViewModelKey
import com.example.movies.ui.details.DetailsSubcomponentProvider
import com.example.movies.ui.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface DetailsModule {

    @DetailsScope
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailsViewModel(vm: DetailsViewModel): ViewModel

    companion object {

        @DetailsScope
        @Provides
        fun provideDetailsSubcomponentProvider(
            application: Application
        ): DetailsSubcomponentProvider {
            return (application as DetailsSubcomponentProvider)
        }

        @DetailsScope
        @Provides
        fun provideGetDetailsUseCse(
            dataSourceRepository: DataSourceRepository
        ): GetDetailsUseCse {
            return GetDetailsUseCse(dataSourceRepository)
        }
    }
}