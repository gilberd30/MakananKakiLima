package com.aplikasi.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.aplikasi.myapplication.R
import com.aplikasi.myapplication.adapter.CategoryProduct
import com.aplikasi.myapplication.adapter.ProductAdapter
import com.aplikasi.myapplication.modal.Category
import com.aplikasi.myapplication.modal.Product
import kotlinx.android.synthetic.main.fragment_home.*

class FragmentProduct : Fragment() {

    var list = ArrayList<Category>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        getCategory()
        return view
    }

    private fun getCategory(){
        var queue:RequestQueue = Volley.newRequestQueue(activity)
        var request = JsonArrayRequest(Request.Method.GET, "http://192.168.100.17:82/store/apicategory.php", null, { response ->
            for(s in 0..response.length() - 1){
                var job = response.getJSONObject(s)
                var id = job.getInt("id")
                var name = job.getString("name")
                var photo = job.getString("photo").replace("localhost","192.168.100.17")


                list.add(Category(id, name, photo))
                var adapterku = CategoryProduct(requireContext(), list)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = adapterku
            }
        }, { error ->
            Log.d("showError", error.toString())
        })
        queue.add(request)
    }
}