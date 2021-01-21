package com.aplikasi.makala.ui.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aplikasi.makala.R
import com.aplikasi.makala.firestore.FirestoreClass
import com.aplikasi.makala.models.User
import com.aplikasi.makala.utils.Contants
import com.aplikasi.makala.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_profile.et_first_name
import kotlinx.android.synthetic.main.activity_user_profile.et_last_name
import kotlinx.android.synthetic.main.activity_user_profile.et_email
import kotlinx.android.synthetic.main.activity_user_profile.iv_user_photo
import kotlinx.android.synthetic.main.activity_user_profile.tv_title
import java.io.IOException

class UserProfileActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mUserDetails: User
    private var mSelectedImageFileUri: Uri? = null
    private  var mUserProfileImageURL:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        if (intent.hasExtra(Contants.EXTRA_USER_DETAILS)){
            mUserDetails = intent.getParcelableExtra(Contants.EXTRA_USER_DETAILS)!!
        }

        et_first_name.setText(mUserDetails.firstName)
        et_last_name.setText(mUserDetails.lastName)

        et_email.isEnabled = false
        et_email.setText(mUserDetails.email)

        if(mUserDetails.profileCompleted == 0){
            tv_title.text = resources.getString(R.string.tittle_complete_profile)
            et_first_name.isEnabled = false
            et_last_name.isEnabled = false
        }else{
            setupActionBar()
            tv_title.text = resources.getString(R.string.tittle_edit_profile)
            GlideLoader(this).loadUserPicture(mUserDetails.image, iv_user_photo)

            if (mUserDetails.mobile != 0L){
                et_mobile_number.setText(mUserDetails.mobile.toString())
            }
            if (mUserDetails.gender == Contants.MALE) {
                rb_male.isChecked = true
            }else{
                rb_female.isChecked = true
            }
        }

        iv_user_photo.setOnClickListener(this)
        btn_save.setOnClickListener(this)
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_user_profile_activity)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }
        toolbar_user_profile_activity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {

                R.id.iv_user_photo -> {
                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                            //showErrorSnackBar("Anda sudah memiliki izin penyimpanan.",false)
                        Contants.showImageChooser(this)
                    }else{
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        Contants.READ_STORAGE_PERMISSION_CODE)
                    }
                }

                R.id.btn_save -> {
                    if(validateUserProfilDetails()){
                        showProgressDialog(resources.getString(R.string.please_wait))
                        FirestoreClass().uploadImageToCloudStorage(this,mSelectedImageFileUri)
                        if(mSelectedImageFileUri != null)
                            FirestoreClass().uploadImageToCloudStorage(this, mSelectedImageFileUri)
                        else{
                            updateUserProfilDetails()
                        }
                    }

                }
            }
        }
    }

    private fun updateUserProfilDetails(){
        val userHashMap = HashMap<String, Any>()

        val firstName = et_first_name.text.toString().trim{ it <= ' '}
        if(firstName != mUserDetails.firstName){
            userHashMap[Contants.FIRST_NAME] = firstName
        }

        val lastName = et_last_name.text.toString().trim{ it <= ' '}
        if(lastName != mUserDetails.lastName){
            userHashMap[Contants.LAST_NAME] = lastName
        }


        val mobileNumber = et_mobile_number.text.toString().trim{ it <= ' '}
        val gender =
                if (rb_male.isChecked) {
            Contants.MALE
        }else{
            Contants.FEMALE
        }

        if(mUserProfileImageURL.isNotEmpty()){
            userHashMap[Contants.IMAGE] = mUserProfileImageURL
        }

        if(mobileNumber.isEmpty() && mobileNumber != mUserDetails.mobile.toString()){
            userHashMap[Contants.MOBILE] = mobileNumber.toLong()
        }

        if(mobileNumber.isEmpty() && gender != mUserDetails.gender){
            userHashMap[Contants.GENDER] = gender
        }

        userHashMap[Contants.GENDER] = gender
        userHashMap[Contants.COMPLETE_PROFILE] = 1
        //showProgressDialog(resources.getString(R.string.please_wait))
        FirestoreClass().updateUserProfileData(this,userHashMap)
    }

    fun userProfileUpdateSuccess() {
        hideProgressDialog()
        Toast.makeText(this,resources.getString(R.string.msg_profil_update_success),Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //showErrorSnackBar("Izin penyimpanan tidak diberikan",false)
            Contants.showImageChooser(this)
        }else{
            Toast.makeText(this,resources.getString(R.string.read_storage_permission_danied),
            Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == Contants.PICK_IMAGE_REQUEST_CODE) {
                if(data != null) {
                    try{
                        mSelectedImageFileUri = data.data!!
                        //iv_user_photo.setImageURI(selectedImageFileuri)
                        GlideLoader(this).loadUserPicture(mSelectedImageFileUri!!, iv_user_photo)
                    }catch (e : IOException) {
                        e.printStackTrace()
                        Toast.makeText(this,resources.getString(R.string.image_selection_failed), Toast.LENGTH_SHORT).show()
                    }
                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                Log.e("Permintaan Dibatalkan","Pemilihan Gambar dibatalkan.")
            }
        }

    }
    private fun validateUserProfilDetails(): Boolean {
        return  when {
            TextUtils.isEmpty(et_mobile_number.text.toString().trim(){it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_mobile_number),true)
                false
            }
            else ->{
                true
            }
        }
    }
    fun imageUploadSuccess(imageURL:String) {
        //hideProgressDialog()
        mUserProfileImageURL = imageURL
        updateUserProfilDetails()
    }
}
