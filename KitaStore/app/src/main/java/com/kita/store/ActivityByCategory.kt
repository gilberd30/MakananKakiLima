package com.kita.store

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.kita.store.adapter.ProductAdapter
import com.kita.store.modal.Product
import kotlinx.android.synthetic.main.fragment_home.*

class ActivityByCategory : AppCompatActivity() {

    var list = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        getProduct()
    }

    private fun getProduct(){
        var queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        var reques = JsonArrayRequest(Request.Method.GET, "http://192.168.100.17:82/store/apiproductid.php?cat_id="+GlobalData.idCategory, null,
            { response ->
                for (s in 0..response.length() - 1){
                    var job = response.getJSONObject(s)
                    var id = job.getInt("id")
                    var name = job.getString("name")
                    var harga = job.getInt("harga")
                    var photo = job.getString("photo").replace("localhost", "192.168.100.17")
                    var deskripsi = job.getString("deskripsi")

                    list.add(Product(id, name, harga, deskripsi, photo))
                    var adapterku = ProductAdapter(applicationContext, list)
                    recycler.layoutManager = LinearLayoutManager(applicationContext)
                    recycler.adapter = adapterku
                }
            },
            { error ->
                Log.d("showError", error.toString())
            })
        queue.add(reques)
    }
}