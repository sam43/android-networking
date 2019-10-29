/*
 * Project : android-networking
 * Developed by Saadat Sayem on 10/29/19 11:27 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 10/29/19 3:33 AM
 */
package com.sam43.android_networking.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sam43.android_networking.R

/**
 * A simple [Fragment] subclass.
 */
class ParentFragment : Fragment() {
    private lateinit var parentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_parent, container, false)
        findNavController().navigate(R.id.action_parentFragment_to_dashboard_navigation)
        return parentView
    }

}
