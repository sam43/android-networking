package com.sam43.android_networking.ui.home.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sam43.android_networking.R
import com.sam43.android_networking.models.MovieDetails
import com.sam43.android_networking.room.Movie
import com.sam43.android_networking.utils.insert
import com.sam43.android_networking.utils.loadImage
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*


class DetailsFragment : Fragment() {
    private lateinit var tmdbViewModel: MovieDetailsViewModel
    private lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_details, container, false)
        initVM()
        return root
    }

    private fun initVM() {
        val movieID = arguments?.get("movieID") as Int
        tmdbViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)

        tmdbViewModel.fetchMovieDetails(movieID)
        tmdbViewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            Log.d("movieDetail: ", "$it")
            updateUI(it)
            //TODO - Your Update UI Logic
            tmdbViewModel.cancelAllRequests()
        })
    }

    private fun updateUI(it: MovieDetails?) {
        setButtonClick(it)
        root.tvTitle.text = it?.respTitle
        //activity?.actionBar?.title = it?.respTitle
        val genres: ArrayList<String> = ArrayList()
        it?.respGenres?.forEach {
            genres.add(it?.respName.toString())
        }

        root.tvGenere.text = genres.joinToString(", ")
        root.tvDetails.text = it?.respOverview

        activity?.loadImage(
            url = it?.respBackdropPath.toString(),
            imageView = ivBG,
            placeHolder = 0,
            errorHolder = R.drawable.ic_placeholder
        )
    }

    private fun setButtonClick(it: MovieDetails?) {
        val movie = Movie(
            id = it?.respId!!,
            name = it.respTitle,
            info = it.respTagline,
            image = it.respPosterPath
        )
        root.btn_add.setOnClickListener {
            activity?.insert(movie)
        }
    }
}
