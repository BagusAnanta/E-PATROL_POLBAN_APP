package com.annas.e_patrolpolbanapp.view.adminShowPetugas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.annas.e_patrolpolbanapp.R
import com.annas.e_patrolpolbanapp.model.ModelDatabaseAdmin

class DataPetugasAdapter(
    var context: Context,
    var modelDatabaseAdmin: MutableList<ModelDatabaseAdmin>,
    var adapterCallBack: AdminAdapterCallback) : RecyclerView.Adapter<DataPetugasAdapter.ViewHolderAdminData>(){

    fun setDataAdminAdapter(items : List<ModelDatabaseAdmin>){
        modelDatabaseAdmin.clear()
        modelDatabaseAdmin.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdminData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_datapetugas_admin, parent,false)
        return ViewHolderAdminData(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAdminData, position: Int) {
        val data = modelDatabaseAdmin[position]
        holder.nama_petugas.text = data.nama_petugas
        holder.no_petugas.text = data.no_petugas
        holder.area_kerja.text = data.area_kerja
        holder.waktu_kerja.text = data.waktu_kerja
    }

    override fun getItemCount(): Int {
        return modelDatabaseAdmin.size
    }

    inner class ViewHolderAdminData(itemView : View) : RecyclerView.ViewHolder(itemView){
        var no_petugas : TextView = itemView.findViewById(R.id.tvNomorPetugas)
        var nama_petugas : TextView = itemView.findViewById(R.id.tvNamaPetugas)
        var area_kerja : TextView = itemView.findViewById(R.id.tvAreaKerja)
        var waktu_kerja : TextView = itemView.findViewById(R.id.tvWaktuKerja)
        var cardview_show : CardView = itemView.findViewById(R.id.cvShow)

        init {
            cardview_show.setOnClickListener {
                val modelCard = modelDatabaseAdmin[adapterPosition]
                adapterCallBack.onDelete(modelCard)
            }
        }
    }

    interface AdminAdapterCallback{
        fun onDelete(modelDatabaseAdmin : ModelDatabaseAdmin?)
    }

}