package com.aplikasi.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aplikasi.myapplication.GlobalData
import com.aplikasi.myapplication.R
import kotlinx.android.synthetic.main.fragment_account.*

class FragmentAccount : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_account, container, false)
        email.text = GlobalData.email
        return view
    }
}