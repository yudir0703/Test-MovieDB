package com.yudi.test3.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.yudi.test3.BuildConfig
import com.yudi.test3.R
import com.yudi.test3.api.models.movies.MovieDetailResponse
import com.yudi.test3.api.models.trending.DataTrending
import com.yudi.test3.app.base.BaseFragment
import com.yudi.test3.app.common.Constant
import com.yudi.test3.app.common.navigateTo
import com.yudi.test3.app.common.setImage
import com.yudi.test3.app.ui.viewmodel.ExploreViewModel
import com.yudi.test3.databinding.DetailMovieFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Yudi Rahmat
 */
class MovieDetailFragment : BaseFragment() {
    private val exploreViewModel: ExploreViewModel by viewModel()
    private lateinit var binding: DetailMovieFragmentBinding
    private lateinit var mContext: Context

    private lateinit var dataItem: DataTrending

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<DetailMovieFragmentBinding>( inflater, R.layout.detail_movie_fragment, container, false).apply {}

        initHeader()
        getBundle()
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun initHeader() {
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        binding.toolbar.navigationIcon = mContext.getDrawable(R.drawable.ic_arrow_back)
        binding.collapsingToolbar.title = ""
        binding.collapsingToolbar.setCollapsedTitleTextColor(ContextCompat.getColor(mContext, R.color.colorWhite))
        binding.collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(mContext, R.color.transparent))

        binding.toolbar.setNavigationOnClickListener { back() }
    }

    private fun getBundle() {
        try {
            val json: String?   = arguments?.getString("json")

            dataItem            = Gson().fromJson(json, DataTrending::class.java)
            val imgUrl: String? = BuildConfig.BASE_IMG_URL + dataItem?.backdropPath.toString()


            setImage(binding.ivPreview, imgUrl, context!!)
            binding.tvRate.text = dataItem?.voteAverage!!.toString()

            fetchDetail(dataItem?.id!!)
        } catch (e: Exception) {
            Log.e(Constant.TAG, getString(R.string.tv_label_log_failed_data_load_tag) + e)
        }
    }

    private fun fetchDetail(movieId: Int) {
        exploreViewModel.loadDetailMovie(movieId)
        exploreViewModel.getMovieDetail().observe(this, Observer {
            it.let {
                setupDetail(it)
            }
        })
    }

    private fun setupDetail(itemData: MovieDetailResponse) {
        val bgPath: String      = itemData?.backdropPath.toString()
        val imgUrl: String?     = BuildConfig.BASE_IMG_URL + if(TextUtils.isEmpty(bgPath) || bgPath.equals("null")) itemData?.posterPath.toString() else bgPath
        val title: String?      = if(TextUtils.isEmpty(itemData?.title)) itemData?.originalName else itemData?.title
        var languages: String   = ""
        var genres: String      = ""

        itemData?.spokenLanguages?.forEach {data -> languages += data.name + ", "}
        if(!TextUtils.isEmpty(languages) && languages.length > 3) languages = languages.substring(0, languages.length-2)

        itemData?.genres?.forEach {data -> genres += data.name + ", "}
        if(!TextUtils.isEmpty(genres) && genres.length > 3) genres = genres.substring(0, genres.length-2)

        setImage(binding.ivPreview, imgUrl, context!!)
        binding.collapsingToolbar.title = title

        binding.tvRate.text         = itemData?.voteAverage!!.toString()

        binding.tvTitle.text        = title
        binding.tvReleaseDate.text  = itemData?.releaseDate
        binding.tvGenre.text        = genres

        binding.tvStatus.text       = itemData?.status
        binding.tvRuntime.text      = itemData?.runtime.toString() + "m"
        binding.tvLanguages.text    = languages
        binding.tvRevenue.text      = itemData?.revenue.toString()

        binding.tvOverview.text     = itemData?.overview.toString()
        binding.tvHomepage.text     = itemData?.homepage.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                back()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)

    }

    private fun back() {
//        (activity as? AppCompatActivity)?.supportFragmentManager?.popBackStack()

        navigateTo(
            this,
            R.id.action_fragDetail_to_fragMain
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}