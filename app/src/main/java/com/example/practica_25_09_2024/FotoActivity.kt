package com.example.practica_25_09_2024

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class FotoActivity : AppCompatActivity() {
    private lateinit var  foto : ImageView
    private  lateinit var btnTomar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto)

        foto = findViewById(R.id.foto)
        btnTomar = findViewById(R.id.btnTomar)

        btnTomar.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            responseLauncher.launch(intent)
        }
    }

    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
          if (activityResult.resultCode == RESULT_OK){
          Toast.makeText(this, "Foto tomada", Toast.LENGTH_SHORT).show()
              val extras = activityResult.data!!.extras
              val imageBitmap = extras!!["data"] as android.graphics.Bitmap?
                foto.setImageBitmap(imageBitmap)
          }else {
              Toast.makeText(this, "Foto no tomada", Toast.LENGTH_SHORT).show()
          }
    }
}