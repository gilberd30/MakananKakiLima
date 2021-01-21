package com.aplikasi.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var registerURL:String = "http://192.168.100.17:82/store/register.php"
        var i = Intent(this,login::class.java)

        buttonRegister.setOnClickListener {
            if(editTextUserName.text.toString().isEmpty() || editEmail.text.toString().isEmpty()|| editPhone.text.toString().isEmpty() || editTextPasword.text.toString().isEmpty()){
                Toast.makeText(applicationContext, "Lengkapi data terlebih dahulu", Toast.LENGTH_LONG).show()
            }else{
                var request:RequestQueue = Volley.newRequestQueue(applicationContext)
                var strRequest = StringRequest(Request.Method.GET, registerURL+"?user="+editTextUserName.text.toString()+"&email="+editEmail.text.toString()+
                "&phone="+editPhone.text.toString()+"&password="+editTextPasword.text.toString(), { response ->
                    if(response.equals("1")){
                        startActivity(i)
                    }else{
                        Toast.makeText(applicationContext, "Sudah Di gunakan", Toast.LENGTH_LONG).show()
                    }
                }, { error ->
                    Log.d("ErrorApss", error.toString())
                })
                request.add(strRequest)
            }
        }
    }
}