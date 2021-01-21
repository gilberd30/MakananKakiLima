package com.aplikasi.makala.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.aplikasi.makala.R

class ProductsFragment : Fragment() {

    //private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.products_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)

        textView.text = "Ini adalah Home Fragment"

        return root
    }
}