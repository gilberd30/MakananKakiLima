package com.aplikasi.myapplication

import android.content.Context
import android.content.Intent
import android.media.MediaCodec
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.ref.ReferenceQueue

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var url:String = "http://192.168.100.17:82/store/login.php"
        var i = Intent(this,MainActivity::class.java)
        buttonLogin.setOnClickListener {
            var request:RequestQueue = Volley.newRequestQueue(applicationContext)

            var stringRequest = StringRequest(Request.Method.GET,url+"?email="+editTextUserName.text.toString()+"&password="+editTextPasword.text.toString(), { response ->
                if(response.equals("0")){
                    Toast.makeText(applicationContext, "Gagal", Toast.LENGTH_LONG).show()
                }else{
                    GlobalData.email =  editTextUserName.toString()
                    startActivity(i)
                }
            }, { error ->
                Log.d("errorApp", error.toString());
            })
            request.add(stringRequest)
        }
    }
}