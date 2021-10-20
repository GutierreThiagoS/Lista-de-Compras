package com.example.listadecompras.framework.dialog

import android.app.AlertDialog
import android.content.Context

fun Context.showDialog(
    message: String,
    title: String? = null,
    position: (() -> Unit)? = null
) {
    val builder = AlertDialog.Builder(this)
    if (title != null) builder.setTitle("Atenção!")
    builder.setMessage(message)
    builder.setNeutralButton (if (position == null) "Ok" else "Cancelar"){ dialog, _ ->
        dialog.dismiss()
    }
    if (position != null) {
        builder.setPositiveButton("Sim") { dialog, _ ->
            position()
            dialog.dismiss()
        }
    }
    builder.create().show()
}