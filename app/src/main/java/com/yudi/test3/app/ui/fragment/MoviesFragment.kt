package com.yudi.test3.app.ui.fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.yudi.test3.R
import com.yudi.test3.api.interfaces.OnItemClickListener
import com.yudi.test3.api.interfaces.ScrollListener
import com.yudi.test3.api.models.trending.DataTrending
import com.yudi.test3.app.base.BaseFragment
import com.yudi.test3.app.common.navigateTo
import com.yudi.test3.app.ui.adapter.MoviesAdapter
import com.yudi.test3.app.ui.viewmodel.ExploreViewModel
import com.yudi.test3.databinding.MoviesFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Yudi Rahmat
 */
class MoviesFragment : BaseFragment() {
    private val exploreViewModel: ExploreViewModel by viewModel()
    private lateinit var binding: MoviesFragmentBinding
    private lateinit var mContext: Context

    private lateinit var listener: ScrollListener

    private lateinit var movieAdapter : MoviesAdapter
    private var listData: ArrayList<DataTrending?> = ArrayList()

    private var searchPage: Int     = 1
    private var isSearch: Boolean   = false
    private var searchQuery: String? = ""

    private var trendingPage: Int   = 1
    private var trendingTotal: Int  = 0

    private var selectedMenu = R.id.nav_home

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<MoviesFragmentBinding>( inflater, R.layout.movies_fragment, container, false).apply {}

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getArgumentData()
        setupMovieAdapter()
        fetchTrending()
        initToolbar()

        initListener()
        onScrollListener()
    }

    private fun getArgumentData() {
        try { selectedMenu = arguments?.getInt("menu")!! } catch (e: Exception) { e.printStackTrace() }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
    }

    private fun fetchTrending() {
        listData.clear()

        isSearch = false

        fetchData()
        exploreViewModel.getTrendingList().observe(viewLifecycleOwner, Observer {
                it.let { it?.forEach {data -> listData.add(data) }
                movieAdapter?.notifyDataSetChanged()
            }
        })

        exploreViewModel.getTrendingTotal().observe(viewLifecycleOwner, Observer {
            it.let { data -> trendingTotal = data }
        })
    }


    private fun fetchData() {
        when (selectedMenu) {
            R.id.nav_home -> {
                exploreViewModel.loadTrending(trendingPage)
            }

            R.id.nav_nowplaying -> {
                exploreViewModel.loadNowPlaying(trendingPage)
            }

            R.id.nav_upcoming -> {
                exploreViewModel.loadUpcoming(trendingPage)
            }

            R.id.nav_popular -> {
                exploreViewModel.loadPopular(trendingPage)
            }
            else -> {
                exploreViewModel.loadTrending(trendingPage)
            }
        }
    }

    private fun fetchSearchMovies(query: String?, page: Int) {
        if(TextUtils.isEmpty(query)) return

        isSearch    = true
        searchQuery = query

        if(page <= 1) listData.clear()
        exploreViewModel.searchMovies(query, page)
        exploreViewModel.getTrendingList().observe(this, Observer {
                it.let { it?.forEach {data -> listData.add(data) }
                movieAdapter?.notifyDataSetChanged()
            }
        })
    }

    private fun setupMovieAdapter() {
        movieAdapter    = MoviesAdapter(listData, object :
            OnItemClickListener {
            override fun onItemClicked(position: Int) {
                val itemData = listData?.get(position)

                val json: String = Gson().toJson(itemData)
                packBundles(json, R.id.action_fragMovie_to_fragDetail)
            }
        })

        binding.rvList.layoutManager = GridLayoutManager(context, 2)
        binding.rvList.adapter = movieAdapter
        movieAdapter.notifyDataSetChanged()
    }

    private fun initListener() {
        setScrolledListener(object :
            ScrollListener {
            override fun onScrolledTop() { }

            override fun onScrolledBottom() {
                val mLayoutManager = binding.rvList.layoutManager as LinearLayoutManager
                val currentLastItemPosition = mLayoutManager.findLastVisibleItemPosition()
                if (listData?.size != trendingTotal &&
                    listData?.size!! > 1 &&
                    currentLastItemPosition >= listData?.size!! - 1) {
                    trendingTotal = listData?.size!!

                    if(isSearch) {
                        searchPage++
                        exploreViewModel.searchMovies(searchQuery, searchPage)
                    } else {
                        trendingPage++
                        fetchData() //exploreViewModel.loadTrending(trendingPage)
                    }
                }
            }

        })
    }

    private fun onScrollListener() {
        binding.nvContent.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight &&
                    scrollY > oldScrollY
                ) {
                    if(listener != null) listener.onScrolledBottom()
                }
            }
        }
    }

    private fun packBundles(json: String, action: Int) {
        val bundle = Bundle()
        bundle.putString("json", json)
        navigateTo(
            this@MoviesFragment,
            action,
            bundle
        )
    }

    private fun clearSearch() {
        searchPage = 1
        trendingPage = 1
        trendingTotal = 0
    }

    private fun searchData(menu: Menu) {
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
            searchView.setOnCloseListener { true }

            val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
            searchPlate.hint = "Search"
            val searchPlateView: View = searchView.findViewById(androidx.appcompat.R.id.search_plate)
            searchPlateView.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.transparent))

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    clearSearch()
                    fetchSearchMovies(query, searchPage)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            // Cancel Search
            searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    clearSearch()
                    fetchTrending()
                    return true
                }
            })

            val searchManager = mContext.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_searchview, menu)

        searchData(menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    fun setScrolledListener(listener: ScrollListener) {
        this.listener = listener
    }
}