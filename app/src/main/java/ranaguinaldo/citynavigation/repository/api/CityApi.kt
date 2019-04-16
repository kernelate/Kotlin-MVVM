package ranaguinaldo.citynavigation.repository.api

import io.reactivex.Observable
import ranaguinaldo.citynavigation.repository.data.City
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi {

    @GET("cities")
    fun getCities(): Observable<List<City>>

    @GET("cities")
    fun searchCity(@Query("q") q : String): Observable<List<City>>
}