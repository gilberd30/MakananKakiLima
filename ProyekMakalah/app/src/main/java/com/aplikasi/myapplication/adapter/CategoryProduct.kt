package com.aplikasi.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.myapplication.R
import com.aplikasi.myapplication.modal.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_category.view.*

class CategoryProduct(var context: Context, var list: ArrayList<Category>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class myCategory(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun category(name:String, photo:String){
            itemView.title_category.text = name
            Picasso.get().load(photo).into(itemView.image_category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view:View = LayoutInflater.from(context).inflate(R.layout.adapter_category, parent, false)

        return ProductAdapter.myProductAdapter(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as myCategory).category(list[position].nama, list[position].photo)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}