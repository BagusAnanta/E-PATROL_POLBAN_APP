package com.annas.e_patrolpolbanapp.database.dao.AdminCreate

import android.content.Context
import androidx.room.Room

class DatabaseClientAdmin private constructor(context : Context){

    var appDatabaseAdmin : AppDatabaseAdmin = Room.databaseBuilder(context,AppDatabaseAdmin::class.java,"create_admin_petugas_db")
        .fallbackToDestructiveMigration()
        .build()

    companion object{
        private var Instance : DatabaseClientAdmin? = null
        @JvmStatic
        @Synchronized
        fun getInstance(context : Context) : DatabaseClientAdmin?{
            if(Instance == null){
                Instance = DatabaseClientAdmin(context)
            }
            return Instance
        }
    }
}