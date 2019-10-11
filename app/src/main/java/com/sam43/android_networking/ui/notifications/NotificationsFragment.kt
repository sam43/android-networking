package com.sam43.android_networking.ui.notifications

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sam43.android_networking.R
import com.sam43.android_networking.utils.CustomUnderlineSpan

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        val spannable: SpannableString? =
            SpannableString("kjadfsfnalskjdfnkja askjdfnkasjdfasjkdfn \n asdfndjlfnasdflkansdflknasdffansdjfn ansdkjfnaskfnsdfnaskdfdansjdfnasdfkjadsf")
        notificationsViewModel.text.observe(this, Observer {
            /*val spannableString = SpannableString(it)
            val dottedUnderlineSpan = CustomUnderlineSpan(Color.RED, 2, 5)
            spannableString.setSpan(dottedUnderlineSpan, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)*/
            //spannable = SpannableString(it)
            //textView.text = it
        })

        spannable?.setSpan(
            CustomUnderlineSpan(Color.RED, 25, 60),
            0, spannable.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        textView.text = spannable
        return root
    }
}