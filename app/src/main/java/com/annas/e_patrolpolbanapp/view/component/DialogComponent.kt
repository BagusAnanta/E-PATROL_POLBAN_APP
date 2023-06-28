package com.annas.e_patrolpolbanapp.view.component

import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogComponent {

    fun DialogComponentOption(
        context : Context,
        title : String = "Dialog Alert",
        message : String,
        iconSet : Int = android.R.drawable.ic_dialog_info,
        onNegativeFunction : () -> Any = {"OnNegatifFunction"},
        onCancel : Boolean = false,
        onPositiveFunction : () -> Any
    ){
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setCancelable(true)
        builder.setNegativeButton("Batal") {
                dialog, which -> if(onCancel) dialog.cancel() else onNegativeFunction
        }
        builder.setPositiveButton("Ya") { dialog, which -> onPositiveFunction}
        val alertDialog = builder.create()
        alertDialog.show()
    }
}