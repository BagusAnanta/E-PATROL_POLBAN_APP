package com.annas.e_patrolpolbanapp.view.adminInputPetugas

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.model.ModelDatabaseAdmin
import com.annas.e_patrolpolbanapp.view.main.MainActivity
import com.annas.e_patrolpolbanapp.viewmodel.AdminViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_input_data_petugas.*
import java.util.Calendar

class InputDataPetugas : AppCompatActivity() {
    lateinit var inputNama : EditText
    lateinit var inputNo : EditText
    lateinit var inputArea : EditText
    lateinit var inputWaktu : EditText
    lateinit var inputUsername : TextInputEditText
    lateinit var inputPassword : TextInputEditText
    lateinit var buttonGetRecordData : Button
    var gettimedata : String = ""
    var listData = ArrayList<ModelDatabaseAdmin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_data_petugas)

        buttonGetRecordData = findViewById(R.id.btnRecord)

        inputWaktu.setOnClickListener {
            gettimedata = getTime()
        }

        insertDataPetugas()

    }

    private fun insertDataPetugas(){
        buttonGetRecordData.setOnClickListener {
            val nama_Petugas = inputNamaPetugas.text.toString()
            val no_Petugas = inputNo.text.toString()
            val area_petugas = inputArea.text.toString()
            val waktu_kerja = inputWaktu.text.toString()

            val username_Petugas_baru = inputUsername.text.toString()
            val password_Petugas_baru = inputPassword.text.toString()

            if(nama_Petugas.isEmpty() || no_Petugas.isEmpty() || area_petugas.isEmpty() || waktu_kerja.isEmpty() || username_Petugas_baru.isEmpty() || password_Petugas_baru.isEmpty()){
                // if a edit text have empty
                Toast.makeText(this,"Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show()
            } else {
                // insert data
                val adminViewModel: AdminViewModel? = null
                adminViewModel?.addDataPetugas(
                    nama_Petugas,
                    no_Petugas,
                    area_petugas,
                    waktu_kerja,
                    username_Petugas_baru,
                    password_Petugas_baru
                )

                val listData = arrayOf(adminViewModel)
                Log.d("Size Data", listData.size.toString())

                Toast.makeText(this,"Data Anda berhasil dimasukkan!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@InputDataPetugas,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getTime() : String{
        val timePicker : TimePickerDialog
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minutes = currentTime.get(Calendar.MINUTE)
        var timeSet = ""

        timePicker = TimePickerDialog(this@InputDataPetugas,object : TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(p0: TimePicker?, hour: Int, minutes: Int) {
                timeSet = String.format("%d : %d",hour,minutes)
            }

        },hour,minutes,false)
        return timeSet
    }
}