package com.annas.e_patrolpolbanapp.view.history

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.model.ModelDatabase
import com.annas.e_patrolpolbanapp.view.component.DialogComponent
import com.annas.e_patrolpolbanapp.view.history.HistoryAdapter.HistoryAdapterCallback
import com.annas.e_patrolpolbanapp.view.main.MainActivity
import com.annas.e_patrolpolbanapp.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.activity_history_patroli.*

class HistoryActivity : AppCompatActivity(), HistoryAdapterCallback {
    var modelDatabaseList: MutableList<ModelDatabase> = ArrayList()
    lateinit var historyAdapter: HistoryAdapter
    lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_patroli)

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

        historyAdapter = HistoryAdapter(this, modelDatabaseList, this)
        rvHistory.setHasFixedSize(true)
        rvHistory.layoutManager = LinearLayoutManager(this)
        rvHistory.adapter = historyAdapter
    }

    private fun setViewModel() {
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        historyViewModel.dataLaporan.observe(this) { modelDatabases: List<ModelDatabase> ->
            if (modelDatabases.isEmpty()) {
                warninglinear.visibility = View.VISIBLE
                rvHistory.visibility = View.GONE
            } else {
                warninglinear.visibility = View.GONE
                rvHistory.visibility = View.VISIBLE
            }
            historyAdapter.setDataAdapter(modelDatabases)
        }
    }

    override fun onDelete(modelDatabase: ModelDatabase?) {
        /*val onPositif = {
           try {
               val uid = modelDatabase!!.uid
               historyViewModel.deleteDataById(uid)
               Toast.makeText(this@HistoryActivity, "Yeay! Data yang dipilih sudah dihapus",Toast.LENGTH_SHORT).show()
           } catch (E: java.lang.NullPointerException){
               Log.d("OnDeleteDataExeception",E.printStackTrace().toString())
           }
        }
        DialogComponent().DialogComponentOption(
            this@HistoryActivity,
            title = "Hapus Riwayat",
            message = "Hapus riwayat ini?",
            iconSet = android.R.drawable.ic_dialog_alert,
            textButtonNegative = "Batal",
            onCancel = true,
            onPositiveFunction = onPositif,
            textButtonPositive = "Ya, Hapus"

        )*/
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
        val intent = Intent(this@HistoryActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}