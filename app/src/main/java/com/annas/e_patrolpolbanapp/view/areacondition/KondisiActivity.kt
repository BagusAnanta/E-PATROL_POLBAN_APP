package com.annas.e_patrolpolbanapp.view.areacondition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.view.adminInputPetugas.InputDataPetugas
import com.annas.e_patrolpolbanapp.view.adminShowPetugas.ShowDataPetugas
import com.annas.e_patrolpolbanapp.view.main.MainActivity
import com.annas.e_patrolpolbanapp.view.reportArea.FireBaseDataClassSafe
import com.annas.e_patrolpolbanapp.view.reportArea.ReportAreaActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class KondisiActivity : AppCompatActivity() {

    lateinit var buttonAman : Button
    lateinit var buttonTidak : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kondisi_area)

        buttonAman = findViewById(R.id.btn_cancel)
        buttonTidak = findViewById(R.id.btn_confirm)

        buttonAman.setOnClickListener {
            val rootNode : FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference : DatabaseReference = rootNode.getReference("UserReportSafe")

            val get_qrdata = intent.getStringExtra("QrCOdeDecode")!!
            val isSafe = "Safe"
            val firebaseDataClassSafe = FireBaseDataClassSafe(get_qrdata,isSafe)
            reference.child(get_qrdata).setValue(firebaseDataClassSafe)

            // toast in here
            Toast.makeText(this,"Daerah Aman, data dimasukkan",Toast.LENGTH_SHORT).show()
            // intent back to mainActivity
            val intent = Intent(this@KondisiActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonTidak.setOnClickListener {
            // in here we intent into reportAreaActivity
            val intent = Intent(this@KondisiActivity,ReportAreaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}