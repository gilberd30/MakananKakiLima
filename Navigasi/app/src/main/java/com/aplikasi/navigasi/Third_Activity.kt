package com.aplikasi.navigasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_third_.*

class Third_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_)

        button_third.setOnClickListener {
            secondActivity()
        }
    }

    fun secondActivity(){
        val intent = Intent(this,Fourth_Activity::class.java)
        startActivity(intent)
    }
}