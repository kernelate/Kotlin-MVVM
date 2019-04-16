package ranaguinaldo.citynavigation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ranaguinaldo.citynavigation.R
import ranaguinaldo.citynavigation.base.BaseViewModel
import ranaguinaldo.citynavigation.repository.api.CityApi
import ranaguinaldo.citynavigation.repository.data.City
import ranaguinaldo.citynavigation.view.CityAdapter
import javax.inject.Inject

class CityListViewModel: BaseViewModel(){

    @Inject lateinit var cityApi: CityApi

    val cityAdapter : CityAdapter = CityAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener { getCities() }


    init {
        getCities()
    }

    fun searchCity(query : String) {
        Log.d("viewmodel", "query : $query")
        subscribe(cityApi.searchCity(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCityListStart() }
            .doOnTerminate { onRetrieveCityListFinish() }
            .subscribe(
                {result -> onRetrieveCityListSuccess(result)},
                {error -> onRetrieveCityListError(error)}
            )
        )
    }


    private fun getCities(){
        subscribe(cityApi.getCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCityListStart() }
            .doOnTerminate { onRetrieveCityListFinish() }
            .subscribe(
                {result -> onRetrieveCityListSuccess(result)},
                {error -> onRetrieveCityListError(error)}
            )
        )
    }

    private fun onRetrieveCityListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCityListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCityListSuccess(cityList : List<City>){
        cityAdapter.updateCityList(cityList)
    }

    private fun onRetrieveCityListError(error : Throwable){
//        errorMessage.value = R.string.post_error
        errorMessage.value = error.toString()
        Log.d("CityListViewModel", error.toString())
    }
}