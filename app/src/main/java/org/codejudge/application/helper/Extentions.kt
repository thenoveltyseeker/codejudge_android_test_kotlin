package org.codejudge.application.helper

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.util.*

/**
 * @author Rolbin, created on 14-11-2020.
 */
fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

//region Glide
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
            .load(url)
            .transition(DrawableTransitionOptions().crossFade(1000))
            .into(this)
}
//endregion

private const val INDIAN_RUPEE = "INR"
fun String.appendCurrency(): String {
    val currency = Currency.getInstance(INDIAN_RUPEE).symbol
    return currency.plus(" $this")
}

