package ranaguinaldo.citynavigation.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.FragmentActivity
import ranaguinaldo.citynavigation.viewmodel.CityListViewModel

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}