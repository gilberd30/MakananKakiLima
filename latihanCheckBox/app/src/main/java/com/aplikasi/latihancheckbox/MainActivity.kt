package com.aplikasi.latihancheckbox

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonLanjut.isEnabled = false
        buttonLanjut.setOnClickListener {
            tampil()
        }

        onoff.setOnCheckedChangeListener { buttonView, b ->
            if (b)
                myRelativeLayout.setBackgroundColor(Color.WHITE)
            else
                myRelativeLayout.setBackgroundColor(Color.BLACK)
        }

        var adapter = ArrayAdapter.createFromResource(this,R.array.negara, android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter


    }
    fun setuju(view: View){
        buttonLanjut.isEnabled = setuju.isChecked
    }
    private fun tampil(){
        Snackbar.make(parent_layout, "Anda Setuju", Snackbar.LENGTH_LONG).show()
    }

    fun setBackgroundColor(view: View) {
        if(red.isChecked)
            parent_layout.setBackgroundColor(Color.RED)
        else if(blue.isChecked)
            parent_layout.setBackgroundColor(Color.BLUE)
        else
            parent_layout.setBackgroundColor(Color.GREEN)
    }

//    private fun getCheckBox(){
//        var text = ""
//        if(jellyBean.isChecked){
//            text += jellyBean.text.toString() + " "
//        }
//        if(kitkat.isChecked){
//            text += kitkat.text.toString() + " "
//        }
//        if(lolipop.isChecked){
//            text += lolipop.text.toString() + " "
//        }
//
//        Snackbar.make(parent_layout, "$text", Snackbar.LENGTH_INDEFINITE)
//            .setAction("Done", View.OnClickListener {
//                jellyBean.isChecked = false
//                kitkat.isChecked = false
//                lolipop.isChecked = false
//            }).show()
//    }
}