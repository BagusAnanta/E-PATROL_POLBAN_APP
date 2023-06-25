package com.annas.e_patrolpolbanapp.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "create_admin_petugas_db")
class ModelDatabaseAdmin : Serializable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid = 0

    @ColumnInfo(name = "nama_petugas")
    lateinit var nama_petugas: String

    @ColumnInfo(name = "no_petugas")
    lateinit var no_petugas: String

    @ColumnInfo(name = "area_kerja")
    lateinit var area_kerja: String

    @ColumnInfo(name = "Waktu_Kerja")
    lateinit var waktu_kerja: String

    @ColumnInfo(name = "keterangan")
    lateinit var keterangan: String

    @ColumnInfo(name = "username")
    lateinit var username: String

    @ColumnInfo(name = "password")
    lateinit var password: String



}