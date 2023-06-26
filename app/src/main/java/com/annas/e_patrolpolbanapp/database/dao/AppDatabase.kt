package com.annas.e_patrolpolbanapp.database

import androidx.room.Database
import com.annas.e_patrolpolbanapp.model.ModelDatabase
import androidx.room.RoomDatabase
import com.annas.e_patrolpolbanapp.database.dao.DatabaseDao

/**
 * Created by Azhar Rivaldi on 19-11-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * LinkedIn : https://www.linkedin.com/in/azhar-rivaldi
 */

@Database(entities = [ModelDatabase::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
}