package com.annas.e_patrolpolbanapp.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.annas.e_patrolpolbanapp.view.login.LoginActivity

/**
 * Created by Azhar Rivaldi on 28-12-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

class SessionLogin(var context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    var PRIVATE_MODE = 0

    fun createLoginSession(nama: String,password : String,login_by : String) {
        editor.putString(KEY_NAMA, nama)
        editor.putString(KEY_PASS,password)
        editor.putString(KEY_LOGIN_BY,login_by)
        editor.putBoolean(IS_LOGIN, true)
        editor.commit()
    }



    fun checkLogin() {
        if (!isLoggedIn()) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun deleteData(){
        editor.clear()
        editor.commit()
    }

    fun isLoggedIn(): Boolean = pref.getBoolean(IS_LOGIN, false)
    fun getNameUser() : String? = pref.getString(KEY_NAMA, "UserName")
    fun getPassword() : String? = pref.getString(KEY_PASS, "Password")
    fun getLoginBy() : String? = pref.getString(KEY_LOGIN_BY, "LoginBy")

    companion object {
        private const val PREF_NAME = "AbsensiPref"
        private const val PREF_PASS = "PasswordPref"
        private const val PREF_LOGIN_BY = "Admin"
        private const val IS_LOGIN = "IsLoggedIn"
        const val KEY_NAMA = "NAMA"
        const val KEY_PASS = "PASS"
        const val KEY_LOGIN_BY = "LOGIN_BY"
    }

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        pref = context.getSharedPreferences(PREF_PASS, PRIVATE_MODE)
        pref = context.getSharedPreferences(PREF_LOGIN_BY, PRIVATE_MODE)
        editor = pref.edit()
    }
}