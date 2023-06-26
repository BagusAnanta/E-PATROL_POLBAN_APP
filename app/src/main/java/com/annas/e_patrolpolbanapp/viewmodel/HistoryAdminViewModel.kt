package com.annas.e_patrolpolbanapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.annas.e_patrolpolbanapp.database.dao.AdminCreate.DatabaseClientAdmin.Companion.getInstance
import com.annas.e_patrolpolbanapp.database.dao.AdminCreate.DatabaseDaoAdmin
import com.annas.e_patrolpolbanapp.model.ModelDatabaseAdmin
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryAdminViewModel(application: Application) : AndroidViewModel(application){
    var dataPegawai : LiveData<List<ModelDatabaseAdmin>>
    var databaseAdminDao : DatabaseDaoAdmin? = getInstance(application)?.appDatabaseAdmin?.databaseDaoAdmin()

    fun deleteDataById(uid : Int){
        Completable.fromAction {
            databaseAdminDao?.deleteDataFromId(uid)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        dataPegawai = databaseAdminDao!!.getAllData()
    }
}