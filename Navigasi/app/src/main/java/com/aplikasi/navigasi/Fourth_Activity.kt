package com.aplikasi.navigasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fourth_.*
import kotlinx.android.synthetic.main.activity_main.*

class Fourth_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth_)

        button_fourth.setOnClickListener {
            secondActivity()
        }
    }

    fun secondActivity(){
        val intent = Intent(this,FinalActivity::class.java)
        startActivity(intent)
    }
}