package com.annas.e_patrolpolbanapp.database.dao.AdminCreate

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.annas.e_patrolpolbanapp.model.ModelDatabaseAdmin

@Dao
interface DatabaseDaoAdmin {
    @Query("SELECT * FROM create_admin_petugas_db")
    fun getAllData(): LiveData<List<ModelDatabaseAdmin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(vararg modelDatabasesAdmin: ModelDatabaseAdmin)

    @Query("DELETE FROM create_admin_petugas_db WHERE uid= :uid")
    fun deleteDataFromId(uid : Int)

    @Query("DELETE FROM create_admin_petugas_db")
    fun deleteAllData()
}