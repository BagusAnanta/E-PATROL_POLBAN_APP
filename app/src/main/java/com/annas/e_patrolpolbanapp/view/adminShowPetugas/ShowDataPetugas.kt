package com.annas.e_patrolpolbanapp.view.adminShowPetugas

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.model.ModelDatabaseAdmin
import com.annas.e_patrolpolbanapp.view.component.DialogComponent
import com.annas.e_patrolpolbanapp.view.main.MainActivity
import com.annas.e_patrolpolbanapp.viewmodel.HistoryAdminViewModel
import kotlinx.android.synthetic.main.activity_history_patroli.rvHistory
import kotlinx.android.synthetic.main.activity_history_patroli.toolbar
import kotlinx.android.synthetic.main.activity_history_patroli.warninglinear

class ShowDataPetugas : AppCompatActivity(), DataPetugasAdapter.AdminAdapterCallback {
    var modelDatabaseList: MutableList<ModelDatabaseAdmin> = ArrayList()
    lateinit var datapetugasadapter: DataPetugasAdapter
    lateinit var historyAdminViewModel: HistoryAdminViewModel

    lateinit var context : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data_petugas)

        setInitLayout()
        setViewModel()
    }

    private fun setInitLayout() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        warninglinear.visibility = View.GONE

        datapetugasadapter = DataPetugasAdapter(this@ShowDataPetugas,modelDatabaseList,this)
        rvHistory.setHasFixedSize(true)
        rvHistory.layoutManager = LinearLayoutManager(this)
        rvHistory.adapter = datapetugasadapter
    }

    private fun setViewModel() {
        historyAdminViewModel = ViewModelProvider(this).get(HistoryAdminViewModel::class.java)
        historyAdminViewModel.dataPegawai.observe(this) { modelDatabasesAdmin: List<ModelDatabaseAdmin> ->
            if (modelDatabasesAdmin.isEmpty()) {
                warninglinear.visibility = View.VISIBLE
                rvHistory.visibility = View.GONE
                Log.d("ModelDatabasesAdminSize", modelDatabasesAdmin.size.toString())
                Toast.makeText(this,"Non Available element ${modelDatabasesAdmin.size}", Toast.LENGTH_SHORT).show()
            } else {
                warninglinear.visibility = View.GONE
                rvHistory.visibility = View.VISIBLE
            }
            datapetugasadapter.setDataAdminAdapter(modelDatabasesAdmin)
        }
    }

    override fun onDelete(modelDatabaseAdmin: ModelDatabaseAdmin?) {
       /* val onPositif = {
            try {
                val uid = modelDatabaseAdmin!!.uid // warning : Potentially null value!!!
                historyAdminViewModel.deleteDataById(uid)
                Toast.makeText(this@ShowDataPetugas, "Yeay! Data yang dipilih sudah dihapus",Toast.LENGTH_SHORT).show()
            } catch (E : java.lang.NullPointerException){
                Log.d("OnDeleteDataExeception",E.printStackTrace().toString())
            }
        }
        DialogComponent().DialogComponentOption(
            this@ShowDataPetugas,
            title = "Hapus Data Petugas",
            message = "Hapus Data Petugas ini?",
            iconSet = android.R.drawable.ic_dialog_alert,
            textButtonNegative = "Batal",
            onCancel = true,
            onPositiveFunction = onPositif,
            textButtonPositive = "Ya, Hapus"
        )*/
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Hapus Data Petugas ini?")
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert)
        alertDialogBuilder.setPositiveButton("Ya, Hapus") { dialogInterface, i ->
            val uid = modelDatabaseAdmin!!.uid
            historyAdminViewModel.deleteDataById(uid)
            Toast.makeText(this@ShowDataPetugas, "Yeay! Data yang dipilih sudah dihapus",
                Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Batal") { dialogInterface, i:
        Int -> dialogInterface.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        // if user back
        val intent = Intent(this@ShowDataPetugas, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}