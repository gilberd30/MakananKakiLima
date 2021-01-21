package com.aplikasi.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pesan.*

class ActivityPesan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan)

        name.text = GlobalData.names
        harga.text = "Rp. "+ GlobalData.hargas.toString()
        deskripsi.text = GlobalData.deskripsis
        Picasso.get().load(GlobalData.photos).into(image)

        pesan.setOnClickListener {
            var hargaProduct = GlobalData.hargas
            var editTextHarga = jumlah.text.toString()
            var convertHarga = editTextHarga.toInt()
            var kalikan = convertHarga * hargaProduct.toInt()

            Log.d("Tampilkan", "${kalikan.toInt()}")
        }
    }
}