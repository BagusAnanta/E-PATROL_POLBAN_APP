package com.annas.e_patrolpolbanapp.view.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.utils.SessionLogin
import com.annas.e_patrolpolbanapp.view.absen.AbsenActivity
import com.annas.e_patrolpolbanapp.view.adminInputPetugas.InputDataPetugas
import com.annas.e_patrolpolbanapp.view.adminShowPetugas.ShowDataPetugas
import com.annas.e_patrolpolbanapp.view.component.DialogComponent
import com.annas.e_patrolpolbanapp.view.login.LoginActivity
import com.annas.e_patrolpolbanapp.view.login.PagePimpinan
import com.annas.e_patrolpolbanapp.view.patroliChecked.PatroliActivity
import com.annas.e_patrolpolbanapp.view.reportArea.ReportAreaActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var strTitle: String
    lateinit var session: SessionLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInitLayout()
    }

    private fun setInitLayout() {
        session = SessionLogin(this)
        session.checkLogin()

        cvPetugasPatroli.setOnClickListener {
            strTitle = "Login Petugas"
            // PatroliActivity Activity
            if (session.getLoginBy().equals("Petugas")) {
                val intent = Intent(this@MainActivity, PatroliActivity::class.java)
                intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Anda Bukan Petugas, tidak dapat masuk",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cvPimpinan.setOnClickListener {
            if (session.getLoginBy().equals("Pimpinan")) {
                strTitle = "Login Pimpinan"
                val intent = Intent(this@MainActivity, PagePimpinan::class.java)
                intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Anda Bukan Pimpinan, tidak dapat masuk",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        cvAdmin.setOnClickListener {
            if (session.getLoginBy().equals("Admin")) {
                checkAdminIsRegister()
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Anda Bukan Admin, tidak dapat masuk",
                    Toast.LENGTH_SHORT
                ).show()
            }
            /* strTitle = "Login Admin"
            val intent = Intent(this@MainActivity, InputDataPetugas::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)*/

        }


        imageLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Yakin Anda ingin Logout?")
            builder.setCancelable(true)
            builder.setNegativeButton("Batal") { dialog, which -> dialog.cancel() }
            builder.setPositiveButton("Ya") { dialog, which ->
                session.logoutUser()
                finishAffinity()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
            /*val onPositif = {
                session.logoutUser()
                finishAffinity()
            }
            DialogComponent().DialogComponentOption(
                this@MainActivity,
                message = "Yakin Anda ingin Logout ?",
                onPositiveFunction = onPositif,
                onCancel = true
            )*/
    }

    private fun checkAdminIsRegister(){
        /*val onPositif = {
            val intent = Intent(this@MainActivity, InputDataPetugas::class.java)
            startActivity(intent)
            finish()
        }

        val onNegatif = {
            val intent = Intent(this@MainActivity, ShowDataPetugas::class.java)
            startActivity(intent)
            finish()
        }

        DialogComponent().
        DialogComponentOption(
            this@MainActivity,
            title = "Register Petugas",
            message = "Apakah Anda Ingin Registrasi Petugas?",
            iconSet = android.R.drawable.ic_dialog_info,
            onNegativeFunction = onNegatif,
            onCancel = false,
            onPositiveFunction = onPositif
        )*/

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Register Petugas")
        builder.setMessage("Apakah Anda Ingin Registrasi Petugas?")
        builder.setIcon(android.R.drawable.ic_dialog_info)

        // if positive button option
        builder.setPositiveButton("Ya"){dialogInterface, which ->
            // we gonna intent in here
            val intent = Intent(this@MainActivity, InputDataPetugas::class.java)
            startActivity(intent)
            finish()
        }

        // if negative button option
        builder.setNegativeButton("Tidak"){ dialogInterface, which ->
            // no Anymore code in here
            val intent = Intent(this@MainActivity, ShowDataPetugas::class.java)
            startActivity(intent)
            finish()
        }

        val alertDialog : AlertDialog = builder.create()
        alertDialog.show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val session = SessionLogin(this@MainActivity)
        session.deleteData()
        val intent = Intent(this@MainActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}