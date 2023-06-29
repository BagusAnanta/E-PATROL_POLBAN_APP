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
        textButtonNegative : String = "Tidak",
        onCancel : Boolean = false,
        onPositiveFunction : () -> Any,
        textButtonPositive : String = "Ya"
    ){
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setCancelable(true)
        builder.setNegativeButton(textButtonNegative) {
                dialog, which -> if(onCancel) dialog.cancel() else dialog.apply { onNegativeFunction }
        }
        builder.setPositiveButton(textButtonPositive) { dialog, which -> dialog.apply { onPositiveFunction }}
        val alertDialog = builder.create()
        alertDialog.show()
    }
}