package com.aplikasi.pertemuan8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private fun myToast(pesan: String, waktu:Int)
            = Toast.makeText(this,pesan,waktu).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

    }
    fun showToast(view: View) {
            myToast("Haloo",Toast.LENGTH_SHORT)
    }
}