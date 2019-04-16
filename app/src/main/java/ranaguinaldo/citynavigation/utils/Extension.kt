package ranaguinaldo.exercise.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import ranaguinaldo.citynavigation.R

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().setCustomAnimations(R.anim.popup_scale_in, R.anim.slide_none).func().commit()
//    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}

fun AppCompatActivity.removeFragment(fragment: Fragment){
    supportFragmentManager.inTransaction { remove(fragment) }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int){
    activity?.supportFragmentManager?.inTransaction { replace(frameId, fragment) }
}

fun Fragment.addFragment(fragment: Fragment, frameId: Int){
    activity?.supportFragmentManager?.inTransaction { add(frameId, fragment) }
}

fun Fragment.removeFragment(fragment: Fragment){
    activity?.supportFragmentManager?.inTransaction { remove(fragment) }
}

fun Fragment.findFragmentByTag(tag : String){
    activity?.supportFragmentManager!!.findFragmentByTag(tag)
}

/**
 * Extension method to provide simpler access to {@link ContextCompat#getColor(int)}.
 */
fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

/**
 *  Extension method to provide simpler access to {@link ContextCompat#getColor(int)}
 *  from a [Fragment].
 */
fun Fragment.getColorCompat(color: Int) = context?.getColorCompat(color)

fun Context.getDrawableCompat(drawableResId: Int): Drawable? = ContextCompat
    .getDrawable(this, drawableResId)

/**
 * Extension method to provide simpler access to {@link ContextCompat#getDrawableCompat(int)}
 * from a [Fragment].
 */
fun Fragment.getDrawableCompat(drawableResId: Int) = context?.getDrawableCompat(drawableResId)!!

/**
 * Extension method to provide show keyboard for View.
 */
fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

/**
 * Extension method to provide show keyboard for View.
 */
fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}