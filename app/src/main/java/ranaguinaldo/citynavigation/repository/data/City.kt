package ranaguinaldo.citynavigation.repository.data

data class City(
    val name : String,
    val subtitle : String,
    val country_code : String,
    val population : Long,
    val longitude : Double,
    val latitude : Double,
    val type : String,
    val id : String,
    val banner : String? = null,
    val zoom : Int,
    val color : String
    )