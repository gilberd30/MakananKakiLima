package com.aplikasi.makala.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.aplikasi.makala.R
import com.bumptech.glide.Glide
import java.io.IOException

class GlideLoader(val context: Context) {

    fun loadUserPicture(image: Any, imageView: ImageView){
        try{
            Glide
                .with(context)
                .load(Uri.parse((image.toString())))
                .centerCrop()
                .placeholder(R.drawable.place_holder_profil_image)
                .into(imageView)
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}