package com.annas.e_patrolpolbanapp.database.dao.AdminCreate

import androidx.room.Database
import androidx.room.RoomDatabase
import com.annas.e_patrolpolbanapp.database.dao.DatabaseDao
import com.annas.e_patrolpolbanapp.model.ModelDatabase
import com.annas.e_patrolpolbanapp.model.ModelDatabaseAdmin

@Database(entities = [ModelDatabaseAdmin::class], version = 1, exportSchema = false)
abstract class AppDatabaseAdmin : RoomDatabase(){
    abstract fun databaseDaoAdmin(): DatabaseDaoAdmin?
}

