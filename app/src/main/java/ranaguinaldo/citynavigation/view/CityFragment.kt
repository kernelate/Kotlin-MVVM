package ranaguinaldo.exercise.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import ranaguinaldo.citynavigation.R
import ranaguinaldo.citynavigation.ToolbarControls
import ranaguinaldo.citynavigation.databinding.FragmentCityBinding
import ranaguinaldo.citynavigation.injection.ViewModelFactory
import ranaguinaldo.citynavigation.viewmodel.CityListViewModel


class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding
    private lateinit var viewModel: CityListViewModel

    private var errorSnackbar: Snackbar? = null

    companion object {
        internal const val TAG: String = "CityFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, ranaguinaldo.citynavigation.R.layout.fragment_city, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.cityList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!)).get(CityListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        (activity as ToolbarControls).showTap(false)
        (activity as ToolbarControls).backPressed(this)


        binding.cityList.setOnTouchListener { view, event ->
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()

    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun showError(error_msg : String){
        errorSnackbar = Snackbar.make(binding.root, error_msg, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }


    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    fun setFilter(filter : String){
        Log.d(TAG, "filter : $filter")
        viewModel.searchCity(filter)
    }

}