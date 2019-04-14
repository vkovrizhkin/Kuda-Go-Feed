package com.angelsit.kudagofeed.view.customview

import android.content.Context
import android.database.DataSetObserver
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import com.angelsit.kudagofeed.R

class PagerIndicator @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr),
        ViewPager.OnAdapterChangeListener,
        ViewPager.OnPageChangeListener {

    private var viewPager: ViewPager? = null
    private val indicatorSize = context.resources.getDimensionPixelSize(R.dimen.indicator_size)
    private val indicatorMargin = context.resources.getDimensionPixelSize(R.dimen.indicator_margin)
    private var dataSetObserver: DataSetObserver? = null

    init {
        gravity = Gravity.CENTER
    }

    fun setViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        this.viewPager?.addOnAdapterChangeListener(this)
        this.viewPager?.addOnPageChangeListener(this)
        unRegisterSetObserver()
        registerSetObserver()
        inflateIndicators()
    }

    override fun onPageScrollStateChanged(p0: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageSelected(position: Int) {
        if (childCount <= 1) return
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child is IndicatorView) {
                child.selectIndicator = i == position
            }
        }
/*        children
            .map { it as IndicatorView }
            .forEachIndexed { index, indicator -> indicator.selectIndicator = index == position }*/
    }

    override fun onAdapterChanged(p0: ViewPager, p1: PagerAdapter?, p2: PagerAdapter?) {
        unRegisterSetObserver()
        registerSetObserver()
        inflateIndicators()
    }

    private fun registerSetObserver() {
        if (dataSetObserver != null) {
            return
        }

        dataSetObserver = object : DataSetObserver() {
            override fun onChanged() {
                inflateIndicators()
            }
        }

        try {
            viewPager?.adapter?.registerDataSetObserver(dataSetObserver as DataSetObserver)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    private fun unRegisterSetObserver() {
        if (dataSetObserver == null || viewPager == null || viewPager?.adapter == null) {
            return
        }

        try {
            viewPager?.adapter?.unregisterDataSetObserver(dataSetObserver as DataSetObserver)
            dataSetObserver = null
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

    }

    private fun inflateIndicators() {
        removeAllViews()
        val layoutParams = LinearLayout.LayoutParams(indicatorSize, indicatorSize)
        layoutParams.marginStart = indicatorMargin / 2
        layoutParams.marginEnd = indicatorMargin / 2
        val pageCount = viewPager?.adapter?.count ?: 0

        (0 until pageCount).forEach { _ ->
            val indicatorView = IndicatorView(context)
            indicatorView.layoutParams = layoutParams
            addView(indicatorView)
        }

        viewPager?.let {
            onPageSelected(it.currentItem)
        }
    }
}