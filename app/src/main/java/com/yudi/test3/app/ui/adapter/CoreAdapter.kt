package com.yudi.test3.app.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yudi.test3.api.interfaces.OnItemClickListener
import com.yudi.test3.api.interfaces.ScrollListener
import java.util.*

/**
 * Created by Yudi Rahmat
 */
open class CoreAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var context: Context? = null
    var list: List<T?>? = ArrayList()

    var listener: OnItemClickListener? = null
    private var scrollListener: ScrollListener? = null
    private var currentTotal: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    /**
     * Add scroll listener with custom interface ScrollListener
     *
     * @param listener As fragmnent, implemented with listener
     * @param dataList As banks of object
     * @return Scroll listener for recyclerview
     */
    fun getScrollListener(listener: ScrollListener?, dataList: List<T>): RecyclerView.OnScrollListener {
        this.list = dataList
        scrollListener = listener
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val mLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                //Scroll down
                if (dy > 0) {
                    //If position equals to last (two) position
                    val currentLastItemPosition = mLayoutManager.findLastVisibleItemPosition()
                    if (list?.size != currentTotal &&
                        list?.size!! > 1 &&
                        currentLastItemPosition >= list?.size!! - 1 && listener != null) {
                        scrollListener?.onScrolledBottom()
                        currentTotal = list?.size!!
                    }
                } else {
                    //If position equals to first position
                    val currentFirstItemPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition()
                    if (currentFirstItemPosition == 0 && scrollListener != null)
                        scrollListener!!.onScrolledTop()
                }
            }
        }
    }
}