package com.aplikasi.makala.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import com.aplikasi.makala.ui.activities.LoginActivity
import com.aplikasi.makala.ui.activities.RegisterActivity
import com.aplikasi.makala.ui.activities.UserProfileActivity
import com.aplikasi.makala.models.User
import com.aplikasi.makala.ui.activities.SettingsActivity
import com.aplikasi.makala.utils.Contants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo:User){
        mFirestore.collection(Contants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener{ e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName, "Terjadi kesalahan saat mendaftarkan Pengguna.",e
                )
            }
    }

    fun getCurrentUserID(): String{
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if(currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun getUserDetails(activity: Activity){
        mFirestore.collection(Contants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())

                val user = document.toObject(User::class.java)!!

                val sharedPreferences =
                    activity.getSharedPreferences(
                        Contants.MAKALA_PREFERENCES,
                        Context.MODE_PRIVATE
                    )

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(
                    Contants.LOGGED_IN_USERNAME,
                    "${user.firstName} ${user.lastName}"
                )
                editor.apply()

                when (activity){
                    is LoginActivity -> {
                        activity.userLoggedInSuccess(user)
                    }
                    is SettingsActivity -> {
                        activity.userDetailsSuccess(user)
                    }
                }
            }
            .addOnFailureListener{ e ->
                when(activity){
                    is LoginActivity -> {
                        activity.hideProgressDialog()
                    }
                }
            }
    }

    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>){
        mFirestore.collection((Contants.USERS)).document(getCurrentUserID())
                .update(userHashMap)
                .addOnSuccessListener {
                    when (activity) {
                        is UserProfileActivity ->{
                            activity.userProfileUpdateSuccess()
                        }
                    }
                }
                .addOnFailureListener { e ->
                    when (activity) {
                        is UserProfileActivity ->{
                            activity.hideProgressDialog()
                        }
                        is SettingsActivity -> {
                            activity.hideProgressDialog()
                        }
                    }

                    Log.e( activity.javaClass.simpleName,"Kesalahan saat memperbarui detail pengguna.")
                }
    }

    fun uploadImageToCloudStorage(activity: Activity, imageFileUri: Uri?){
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            Contants.USER_PROFILE_IMAGE + System.currentTimeMillis() + "."
        + Contants.getFileExtensions(activity,imageFileUri)
        )
        sRef.putFile(imageFileUri!!).addOnSuccessListener { taskSnapshot ->
            Log.e("Firebase Image URL",taskSnapshot.metadata!!.reference!!.downloadUrl.toString())
            taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                Log.e("Downloadable Image URL", uri.toString())
                when (activity) {
                    is UserProfileActivity ->{ activity.imageUploadSuccess(uri.toString())}
                }
            }
        }
            .addOnFailureListener{exception ->
                when (activity) {
                    is UserProfileActivity -> {activity.hideProgressDialog()}
                }
                Log.e(activity.javaClass.simpleName, exception.message,exception)
            }

    }
}