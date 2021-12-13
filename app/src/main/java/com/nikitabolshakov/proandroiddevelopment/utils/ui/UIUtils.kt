package com.nikitabolshakov.proandroiddevelopment.utils.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.nikitabolshakov.proandroiddevelopment.R

fun getStubAlertDialog(context: Context): AlertDialog = getAlertDialog(context, null, null)

fun getAlertDialog(
    context: Context,
    title: String?,
    message: String?
): AlertDialog {
    val builder = AlertDialog.Builder(context)
    var finalTitle: String? = context.getString(R.string.dialog_title_stub)
    if (!title.isNullOrBlank()) {
        finalTitle = title
    }
    builder.setTitle(finalTitle)
    if (!message.isNullOrBlank()) {
        builder.setMessage(message)
    }
    builder.setCancelable(true)
    builder.setPositiveButton(R.string.dialog_button_cancel) { dialog, _ -> dialog.dismiss() }
    return builder.create()
}