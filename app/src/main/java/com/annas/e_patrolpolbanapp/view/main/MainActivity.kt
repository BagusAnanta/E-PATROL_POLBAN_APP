package com.annas.e_patrolpolbanapp.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.utils.SessionLogin
import com.annas.e_patrolpolbanapp.view.absen.AbsenActivity
import com.annas.e_patrolpolbanapp.view.adminInputPetugas.InputDataPetugas
import com.annas.e_patrolpolbanapp.view.adminShowPetugas.ShowDataPetugas
import com.annas.e_patrolpolbanapp.view.component.DialogComponent
import com.annas.e_patrolpolbanapp.view.login.PagePimpinan
import com.annas.e_patrolpolbanapp.view.patroliChecked.PatroliActivity
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
            val intent = Intent(this@MainActivity, PatroliActivity::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        cvPimpinan.setOnClickListener {
            strTitle = "Login Pimpinan"
            val intent = Intent(this@MainActivity, PagePimpinan::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        cvAdmin.setOnClickListener {
           /* strTitle = "Login Admin"
            val intent = Intent(this@MainActivity, InputDataPetugas::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)*/
            checkAdminIsRegister()
        }


        imageLogout.setOnClickListener {
           /* val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Yakin Anda ingin Logout?")
            builder.setCancelable(true)
            builder.setNegativeButton("Batal") { dialog, which -> dialog.cancel() }
            builder.setPositiveButton("Ya") { dialog, which ->
                session.logoutUser()
                finishAffinity()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }*/
            val onPositif = {
                session.logoutUser()
                finishAffinity()
            }
            DialogComponent().DialogComponentOption(this@MainActivity, message = "Yakin Anda ingin Logout ?", onPositiveFunction = onPositif, onCancel = true)
        }
    }

    private fun checkAdminIsRegister(){
        val onPositif = {
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
        )

    }

}