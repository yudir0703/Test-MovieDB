package com.yudi.test3.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yudi.test3.BuildConfig
import com.yudi.test3.R
import com.yudi.test3.api.interfaces.OnItemClickListener
import com.yudi.test3.api.models.trending.DataTrending
import com.yudi.test3.app.common.setImage
import kotlinx.android.synthetic.main.view_row_movie.view.*


/**
 * @author Yudi Rahmat
 */

class MoviesAdapter(listData: MutableList<DataTrending?>?, listener: OnItemClickListener): CoreAdapter<DataTrending>() {
    private val layoutStory = R.layout.view_row_movie

    init {
        this.list = listData
        this.listener   = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return ViewHolder(LayoutInflater.from(parent.context).inflate(layoutStory, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind()
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val item: DataTrending? = list?.get(adapterPosition)

            val imgUrl = BuildConfig.BASE_IMG_URL + item?.posterPath.toString()

            setImage(itemView.iv_preview, imgUrl, context!!)
            itemView.tv_rate.text = item?.voteAverage!!.toString()

            itemView.setOnClickListener {
                listener?.onItemClicked(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }
}
