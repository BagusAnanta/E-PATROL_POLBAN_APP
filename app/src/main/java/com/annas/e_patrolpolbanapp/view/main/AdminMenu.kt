package com.annas.e_patrolpolbanapp.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.view.adminInputPetugas.InputDataPetugas
import com.annas.e_patrolpolbanapp.view.adminShowPetugas.ShowDataPetugas
import com.annas.e_patrolpolbanapp.view.qrgenerate.QrGenerate
import kotlinx.android.synthetic.main.activity_admin_menu.*

class AdminMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)

        setInitLayout()
    }

    private fun setInitLayout(){

        cvQrGenerator.setOnClickListener {
            // intent into QR generator
            val intent = Intent(this@AdminMenu,QrGenerate::class.java)
            startActivity(intent)
            finish()
        }

        cvDataPetugas.setOnClickListener {
            // intent into Data petugas
            val intent = Intent(this@AdminMenu,ShowDataPetugas::class.java)
            startActivity(intent)
            finish()
        }

        cvRegisterPetugas.setOnClickListener {
            // intent into Register petugas
            val intent = Intent(this@AdminMenu,InputDataPetugas::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // if a back we intent into mainmenu and finish
        val intent = Intent(this@AdminMenu,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}