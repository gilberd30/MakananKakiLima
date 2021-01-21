package com.latihan.tugasminggu4

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hello.text = "Nama : Gilbert, Nim : 181113031"
        this.title = "Pertemuan 4"
        Log.w(Tag, "Gilbert(181113031)", )
        Log.d(Tag, "Gilbert(181113031)", )
        Log.e(Tag, "Gilbert(181113031)", )
        Log.v(Tag, "Gilbert(181113031)", )
        Log.i(Tag, "Gilbert(181113031)", )
    }
    companion object{
        val Tag:String = MainActivity::class.java.simpleName
    }
}