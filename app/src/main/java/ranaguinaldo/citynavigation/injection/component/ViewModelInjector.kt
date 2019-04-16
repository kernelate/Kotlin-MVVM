package ranaguinaldo.citynavigation.injection.component

import dagger.Component
import ranaguinaldo.citynavigation.injection.module.NetworkModule
import ranaguinaldo.citynavigation.viewmodel.CityListViewModel
import ranaguinaldo.citynavigation.viewmodel.CityViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(cityListViewModel: CityListViewModel)

    fun inject(cityViewModel: CityViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}