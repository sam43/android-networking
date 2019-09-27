package com.sam43.android_networking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        findNavController().navigate(R.id.action_parentFragment_to_navigation_dashboard)
        return parentView
    }

}
