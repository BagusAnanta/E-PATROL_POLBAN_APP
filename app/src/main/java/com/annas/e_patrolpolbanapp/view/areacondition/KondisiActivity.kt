package com.annas.e_patrolpolbanapp.view.areacondition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.view.reportArea.ReportAreaActivity

class KondisiActivity : AppCompatActivity() {

    lateinit var buttonAman : Button
    lateinit var buttonTidak : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kondisi_area)

        buttonAman = findViewById(R.id.btn_cancel)
        buttonTidak = findViewById(R.id.btn_confirm)

        buttonAman.setOnClickListener {
            // in here we input a data into database
            /*
            * maybe content in here
            * 1. Lokasi , Area
            * 2. Kondisi */
        }

        buttonTidak.setOnClickListener {
            // in here we intent into reportAreaActivity
            val intent = Intent(this@KondisiActivity,ReportAreaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}