package com.annas.e_patrolpolbanapp.view.patroliChecked

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.view.absen.AbsenActivity
import com.annas.e_patrolpolbanapp.view.history.HistoryActivity

class PatroliActivity : AppCompatActivity() {

    lateinit var buttonAbsen : Button
    lateinit var buttonHistoryPatrol : Button
    var stringtext : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patroli_activty)

        buttonAbsen = findViewById(R.id.btnAbsensi)
        buttonHistoryPatrol = findViewById(R.id.btnHistory)

        buttonAbsen.setOnClickListener {
            stringtext = "Verifikasi Petugas"
            val intent = Intent(this@PatroliActivity,AbsenActivity::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE,stringtext)
            startActivity(intent)
            finish()
        }

        buttonHistoryPatrol.setOnClickListener {
            val intent = Intent(this@PatroliActivity,HistoryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}