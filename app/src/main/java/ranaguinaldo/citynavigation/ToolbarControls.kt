package ranaguinaldo.citynavigation

import android.support.v4.app.Fragment

interface ToolbarControls {

    fun showTap(show : Boolean)

    fun backPressed(fragment : Fragment)

}