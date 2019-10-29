/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/28/19 5:14 PM
 */
package com.sam43.android_networking.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sam43.android_networking.R
import com.sam43.android_networking.models.MovieItem
import com.sam43.android_networking.utils.RecyclerAdapterUtil
import com.sam43.android_networking.utils.loadCircularImage
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var tmdbViewModel: HomeViewModel
    private lateinit var root: View
    private val movieListObserver = Observer<MutableList<MovieItem?>?> {
        updateUI(it)
        //TODO - Your Update UI Logic
        tmdbViewModel.cancelAllRequests()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tmdbViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_home, container, false)
        initVM()
        return root
    }

    private fun initVM() {
        tmdbViewModel = ViewModelProviders.of(this@HomeFragment).get(HomeViewModel::class.java)

        tmdbViewModel.fetchMovies()

        tmdbViewModel.popularMoviesLiveData.observe(viewLifecycleOwner, movieListObserver)
    }

    private fun updateUI(list: MutableList<MovieItem?>?) {
        val viewList = listOf(
            R.id.movie_image,
            R.id.movie_title,
            R.id.movie_details
        )
        list?.toList()?.let {
            RecyclerAdapterUtil.Builder(context!!, it, R.layout.item_movie_list)
                .viewsList(viewList)
                .bindView { _, item, _, innerViews ->
                    val movieTitle = innerViews[R.id.movie_title] as TextView
                    val movieDetails = innerViews[R.id.movie_details] as TextView
                    val movieImage = innerViews[R.id.movie_image] as ImageView

                    movieTitle.text = item?.respOriginalTitle
                    movieDetails.text = item?.respOriginalTitle
                    activity?.loadCircularImage(
                        item?.respPosterPath,
                        movieImage,
                        0,
                        R.drawable.ic_placeholder
                    )

                }
                .addClickListener { item, _ ->
                    val bundle = bundleOf(
                        "movieID" to item?.respId,
                        "movieTitle" to item?.respOriginalTitle
                    )
                    root.findNavController()
                        .navigate(R.id.action_navigation_home_to_detailsFragment, bundle)
                }
                .into(root.rv_popular_movies)
        }
        root.rv_popular_movies.layoutManager = LinearLayoutManager(context)
        root.rv_popular_movies.setHasFixedSize(true)
    }
}