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
import kotlinx.android.synthetic.main.kondisi_area.*

class KondisiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kondisi_area)

        // in this firebase please do not content a symbol because can potentially exception

        btn_cancel.setOnClickListener {
            val rootNode : FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference : DatabaseReference = rootNode.getReference("UserReportSafe")

            val get_qrdata = intent.getStringExtra("QrCOdeDecode").toString()!!
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

        btn_confirm.setOnClickListener {
            // in here we intent into reportAreaActivity
            val intent = Intent(this@KondisiActivity,ReportAreaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}