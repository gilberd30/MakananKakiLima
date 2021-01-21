package com.aplikasi.navigasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        button_second.setOnClickListener {
            secondActivity()
        }
    }

    fun secondActivity(){
        val intent = Intent(this,Third_Activity::class.java)
        startActivity(intent)
    }
}