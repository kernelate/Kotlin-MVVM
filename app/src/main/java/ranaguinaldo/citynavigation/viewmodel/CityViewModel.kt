package ranaguinaldo.citynavigation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.view.View
import ranaguinaldo.citynavigation.base.BaseViewModel
import ranaguinaldo.citynavigation.repository.data.City


class CityViewModel : BaseViewModel(){

    private val cityName = MutableLiveData<String>()
    private val citySubtitle = MutableLiveData<String>()
    private val cityCountryCode = MutableLiveData<String>()
    private val cityPopulation = MutableLiveData<Long>()
    private val cityLongitude = MutableLiveData<Double>()
    private val cityLatitude = MutableLiveData<Double>()
    private val cityType = MutableLiveData<String>()
    private val cityID = MutableLiveData<String>()
    private val cityBanner = MutableLiveData<String>()
    private val cityZoom = MutableLiveData<Int>()
    private val cityColor = MutableLiveData<String>()

    val imageViewVisibility : MutableLiveData<Int> = MutableLiveData()
    val textViewVisibility : MutableLiveData<Int> = MutableLiveData()



    fun bind(city: City){
        cityName.value = city.name
        citySubtitle.value = city.subtitle
        cityCountryCode.value = city.country_code
        cityPopulation.value = city.population
        cityLongitude.value = city.longitude
        cityLatitude.value = city.latitude
        cityType.value = city.type
        cityID.value = city.id
        cityBanner.value = city.banner
        cityZoom.value = city.zoom
        cityColor.value = city.color

        if(city.banner.isNullOrBlank()){
            imageViewVisibility.value = View.GONE
            textViewVisibility.value = View.VISIBLE
        } else {
            imageViewVisibility.value = View.VISIBLE
            textViewVisibility.value = View.GONE
        }
    }

    fun getCityName():MutableLiveData<String>{
        return cityName
    }

    fun getCitySubtitle():MutableLiveData<String>{
        return citySubtitle
    }

    fun getCityCountryCode():MutableLiveData<String>{
        return cityCountryCode
    }

    fun getCityPopulation():MutableLiveData<Long>{
        return cityPopulation
    }

    fun getCityLongitude():MutableLiveData<Double>{
        return cityLongitude
    }

    fun getCityLatitude():MutableLiveData<Double>{
        return cityLatitude
    }

    fun getCityType():MutableLiveData<String>{
        return cityType
    }

    fun getCityID():MutableLiveData<String>{
        return cityID
    }

    fun getCityBanner():MutableLiveData<String>{
        return cityBanner
    }

    fun getCityZoom():MutableLiveData<Int>{
        return cityZoom
    }

    fun getCityColor():MutableLiveData<String>{
        return cityColor
    }

}