package com.aplikasi.makala.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.aplikasi.makala.R
import com.aplikasi.makala.firestore.FirestoreClass
import com.aplikasi.makala.models.User
import com.aplikasi.makala.utils.Contants
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_password

class LoginActivity : BaseActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        tv_forgot_password.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
    }

    fun userLoggedInSuccess(user: User){
        hideProgressDialog()
        if(user.profileCompleted == 0){
            val intent = Intent(this, UserProfileActivity::class.java)
            intent.putExtra(Contants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        }else{
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        finish()
    }

    override fun onClick(view: View?) {
        if(view != null){
            when (view.id){
                R.id.tv_forgot_password -> {
                    val intent = Intent(this, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                R.id.btn_login -> {
                    logInRegisteredUser()
                }
                R.id.tv_register -> {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_email.text.toString().trim() { it <= ' '}) ->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email),true)
                false
            }
            TextUtils.isEmpty(et_password.text.toString().trim(){ it <= ' '})->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password),true)
                false
            }
            else -> {

                true
            }
        }
    }

    private fun logInRegisteredUser(){
        if(validateLoginDetails()){
            showProgressDialog(resources.getString(R.string.please_wait))
            val email: String = et_email.text.toString().trim(){it <= ' '}
            val password: String = et_password.text.toString().trim(){it <= ' '}

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            FirestoreClass().getUserDetails(this)
                        }else{
                            hideProgressDialog()
                            showErrorSnackBar(task.exception!!.message.toString(),true)
                        }

                    }
        }
    }
}