/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/29/19 11:25 AM
 */

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
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sam43.android_networking.R
import com.sam43.android_networking.room.AppDataBase
import com.sam43.android_networking.room.Movie
import com.sam43.android_networking.utils.RecyclerAdapterUtil
import com.sam43.android_networking.utils.loadCircularImage
import com.sam43.android_networking.workmanager.BackgroundWorker
import com.sam43.android_networking.workmanager.DaggerWorkerComponent
import com.sam43.android_networking.workmanager.Manager
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class DashboardFragment : Fragment() {

    @Inject
    lateinit var workManagerInstance: Manager

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var db: AppDataBase
    private lateinit var root: View

    override fun onAttach(context: Context) {
        DaggerWorkerComponent.create().push(this)
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
        initWorker(workManagerInstance.instance())
        initVM()
        return root
    }

    private fun initWorker(workManagerInstance: WorkManager) {
        val bgWorkRequest = OneTimeWorkRequestBuilder<BackgroundWorker>()
            .build()
        workManagerInstance.enqueue(bgWorkRequest)
        workManagerInstance.getWorkInfoByIdLiveData(bgWorkRequest.id)
            .observe(viewLifecycleOwner, Observer {
                toast("State: ${it.state}")
                if (it?.state.toString().equals("SUCCEEDED", ignoreCase = true))
                    workManagerInstance.cancelWorkById(bgWorkRequest.id)
            })
    }

    private fun initVM() {
        dashboardViewModel.fetchAddedMovies(activity!!)
        dashboardViewModel.addedMovieList.observe(this, Observer {
            //textView.text = it
            if (it.isNotEmpty()) updateUI(it) else toast("list is empty")
            dashboardViewModel.cancelAllRequests()
        })
    }

    private fun updateUI(list: List<Movie?>?) {
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