package com.sam43.android_networking.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sam43.android_networking.R
import com.sam43.android_networking.room.AppDataBase
import com.sam43.android_networking.room.User
import com.sam43.android_networking.utils.RecyclerAdapterUtil
import com.sam43.android_networking.utils.loadCircularImage
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import org.jetbrains.anko.support.v4.toast

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var db: AppDataBase
    private lateinit var root: View

    override fun onAttach(context: Context) {
        db = AppDataBase.invoke(context)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        initVM()
        return root
    }

    private fun initVM() {
        dashboardViewModel.fetchAddedMovies(activity!!)
        dashboardViewModel.addedMovieList.observe(this, Observer {
            //textView.text = it
            if (it.isNotEmpty()) updateUI(it) else toast("list is empty")
            dashboardViewModel.cancelAllRequests()
        })
    }

    private fun updateUI(list: List<User?>?) {
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

                    movieTitle.text = item?.name
                    movieDetails.text = item?.info
                    activity?.loadCircularImage(
                        item?.image,
                        movieImage,
                        0,
                        R.drawable.ic_placeholder
                    )

                }
                .into(root.rvUserList)
        }
        root.rvUserList.layoutManager = LinearLayoutManager(context)
        root.rvUserList.setHasFixedSize(true)
    }
}