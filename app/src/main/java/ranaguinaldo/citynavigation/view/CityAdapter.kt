package ranaguinaldo.citynavigation.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import ranaguinaldo.citynavigation.R
import ranaguinaldo.citynavigation.databinding.FragmentRowTestBinding
import ranaguinaldo.citynavigation.repository.data.City
import ranaguinaldo.citynavigation.viewmodel.CityViewModel

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>(){

    private val TAG : String = "CityAdapter"

    private lateinit var cityList : List<City>

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CityAdapter.CityViewHolder {
        val binding: FragmentRowTestBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.fragment_row_test, parent, false)
        Log.d("HomeAdapter", "onCreateViewHolder")
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int = if(::cityList.isInitialized) cityList.size else 0

    fun updateCityList(cityList : List<City>){
        this.cityList = cityList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CityAdapter.CityViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    inner class CityViewHolder (private val binding: FragmentRowTestBinding) : RecyclerView.ViewHolder(binding.root){
        private val viewModel = CityViewModel()

        fun bind(post:City){
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}