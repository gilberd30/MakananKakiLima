package com.aplikasi.latihanbutton

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonRandomForeground.setOnClickListener {
            textView1.setTextColor(Color.rgb(acakWarna(),acakWarna(),acakWarna()))
        }

        buttonRandomBackground.setOnClickListener {
            textView1.setBackgroundColor(Color.rgb(acakWarna(),acakWarna(),acakWarna()))
        }

        var tamabah = 0
        fabArrowUp.setOnClickListener {
            tamabah++
            textView1.text = "Count : " + tamabah.toString()
        }
        fabArrowDown.setOnClickListener {
            tamabah--
            textView1.text = "Count : " + tamabah.toString()
        }

        buttonFloat.setOnClickListener {
            tamabah = 0
            textView1.text = "Count : " + tamabah.toString()
        }
        buttonInt.setOnClickListener {
            tamabah = 1
            textView1.text = "Count : " + tamabah.toString()
        }
    }



    fun acakWarna():Int{
        return Random.nextInt(0,255)
    }

}