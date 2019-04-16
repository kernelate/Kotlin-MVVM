package ranaguinaldo.citynavigation

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife
import com.android.databinding.library.baseAdapters.BR
import kotlinx.android.synthetic.main.activity_main.*
import ranaguinaldo.citynavigation.databinding.ActivityMainBinding
import ranaguinaldo.exercise.utils.gone
import ranaguinaldo.exercise.utils.removeFragment
import ranaguinaldo.exercise.utils.replaceFragment
import ranaguinaldo.exercise.utils.visible
import ranaguinaldo.exercise.view.CityFragment
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class MainActivity : AppCompatActivity(), ToolbarControls {

    private val TAG: String = "MainActivity"

    private lateinit var cityFragment: CityFragment
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = SearchViewModel()
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        tap.setOnClickListener {
            tap.gone()
            cityFragment = CityFragment()
            replaceFragment(R.id.container, cityFragment)
        }


    }

    override fun showTap(show: Boolean) {

        when (show) {
            TRUE -> {
                Log.d(TAG, "show: $show")
                searchCity.gone()
                tap.visible()
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            FALSE -> {
                Log.d(TAG, "show: $show")
                tap.gone()
                searchCity.visible()
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun backPressed(fragment: Fragment) {
        toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.findFragmentById(fragment.id) != null) {
                Log.d(TAG, "fragment: $fragment")
                showTap(true)
                removeFragment(fragment)
            }
        }

    }

    inner class SearchViewModel : BaseObservable() {

        var query : String = ""
        @Bindable get
        set(value) {
            field = value
            Log.d(TAG, "field: $value")
            cityFragment.setFilter(value)
            this.notifyPropertyChanged(BR.query)
        }

    }

}
