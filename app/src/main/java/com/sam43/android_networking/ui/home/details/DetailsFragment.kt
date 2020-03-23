package com.sam43.android_networking.ui.home.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sam43.android_networking.R
import com.sam43.android_networking.models.MovieDetails
import com.sam43.android_networking.room.Movie
import com.sam43.android_networking.utils.delete
import com.sam43.android_networking.utils.insert
import com.sam43.android_networking.utils.loadImage
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*


class DetailsFragment : Fragment() {
    private lateinit var tmdbViewModel: MovieDetailsViewModel
    private lateinit var root: View
    private var toggled: Boolean = false
    private val moveDetailsObserver: Observer<MovieDetails?> = Observer {
        updateUI(it)
    }
    private val isAlreadyFavoriteObserver = Observer<Boolean> { alreadyAdded ->
        Log.d("isAlreadyFavoriteObs", "value from observer: $alreadyAdded")
        toggled = alreadyAdded
        if (alreadyAdded) {
            root.btn_add.text = "Remove"
        } else {
            root.btn_add.text = "Add"
        }
    }

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
        tmdbViewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        tmdbViewModel.fetchMovieDetails(movieID)
        tmdbViewModel.checkIfAlreadyInFavorites(requireContext(), movieID)
        tmdbViewModel.movieDetails.observe(viewLifecycleOwner, moveDetailsObserver)

        tmdbViewModel.isFavorite.observe(viewLifecycleOwner, isAlreadyFavoriteObserver)
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
            if (!toggled) addToFavorites(movie) else deleteFromFavorites(movie)
        }
    }

    private fun deleteFromFavorites(movie: Movie) {
        activity?.delete(movie)
        root.btn_add.text = "Add"
    }

    private fun addToFavorites(movie: Movie) {
        activity?.insert(movie)
        root.btn_add.text = "Remove"
    }
}
