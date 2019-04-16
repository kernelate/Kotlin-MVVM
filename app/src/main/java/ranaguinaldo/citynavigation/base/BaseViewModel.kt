package ranaguinaldo.citynavigation.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ranaguinaldo.citynavigation.injection.component.DaggerViewModelInjector
import ranaguinaldo.citynavigation.injection.component.ViewModelInjector
import ranaguinaldo.citynavigation.injection.module.NetworkModule
import ranaguinaldo.citynavigation.viewmodel.CityListViewModel
import ranaguinaldo.citynavigation.viewmodel.CityViewModel

abstract class BaseViewModel : ViewModel() {

    val subscriptions = CompositeDisposable()

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CityListViewModel -> injector.inject(this)
            is CityViewModel -> injector.inject(this)
        }
    }


    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}