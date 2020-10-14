package com.test.pokedex.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.test.pokedex.R
import com.test.pokedex.utils.GlideApp
import kotlinx.android.synthetic.main.item_image.view.*

/**
 * Simple ViewPager Adapter to show a list of images
 */
class ImagesPagerAdapter(val context: Context, val images: List<String>) :
    PagerAdapter() {

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val viewLayout: View = inflater.inflate(R.layout.item_image, container, false)

        GlideApp.with(context).load(images[position]).into(viewLayout.pokemonImage)
        container.addView(viewLayout)

        return viewLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any) = view == obj

}