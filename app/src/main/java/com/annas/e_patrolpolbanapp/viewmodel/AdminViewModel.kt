package com.annas.e_patrolpolbanapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.annas.e_patrolpolbanapp.database.dao.AdminCreate.DatabaseClientAdmin.Companion.getInstance
import com.annas.e_patrolpolbanapp.database.dao.AdminCreate.DatabaseDaoAdmin
import com.annas.e_patrolpolbanapp.model.ModelDatabaseAdmin
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class AdminViewModel(applcation : Application) : AndroidViewModel(applcation) {
    var databaseDaoadmin : DatabaseDaoAdmin? = getInstance(applcation)?.appDatabaseAdmin?.databaseDaoAdmin()

    fun addDataPetugas(
        nama : String,
        nomor : String,
        area_kerja : String,
        waktu_kerja : String,
        username : String,
        password : String
    ){
        Completable.fromAction {
            val modelDatabaseAdmin = ModelDatabaseAdmin()
            modelDatabaseAdmin.nama_petugas = nama
            modelDatabaseAdmin.no_petugas = nomor
            modelDatabaseAdmin.area_kerja = area_kerja
            modelDatabaseAdmin.waktu_kerja = waktu_kerja
            modelDatabaseAdmin.username = username
            modelDatabaseAdmin.password = password
            databaseDaoadmin?.insertData(modelDatabaseAdmin)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}
