package com.yudi.test3.app.modules

import com.yudi.test3.BuildConfig
import com.yudi.test3.api.repository.ExploreRepositories
import com.yudi.test3.app.ui.viewmodel.ExploreViewModel
import com.yudi.test3.service.api.APIInterface
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * @author Yudi Rahmat
 */

val AppModule: Module = module {
    single {
        APIInterface(
            BuildConfig.BASE_URL,
            androidContext()
        )
    }

    single {
        ExploreRepositories(
            get(),
            CompositeDisposable()
        )
    }

    viewModel {
        ExploreViewModel(get())
    }
}