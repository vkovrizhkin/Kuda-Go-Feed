package com.angelsit.kudagofeed.view

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.angelsit.kudagofeed.R
import com.bumptech.glide.Glide

class PhotosViewPagerAdapter : androidx.viewpager.widget.PagerAdapter() {

    private var photoList: List<String> = emptyList()

    fun setPhotos(photoList: List<String>){
        this.photoList = photoList
        notifyDataSetChanged()
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        (collection as androidx.viewpager.widget.ViewPager).removeView(view as View)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean  = p0 ==p1

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = buildPageContentView(container.context, photoList[position])
        container.addView(view)
        return view
    }

    override fun getCount(): Int = photoList.size

    private fun buildPageContentView(context: Context, photoUrl: String): View {
        return ImageView(context).apply {
            Glide.with(context)
                .load(photoUrl)
                .placeholder(R.drawable.ic_camera_alt)
                .centerCrop()
                .into(this)
        }
    }
}