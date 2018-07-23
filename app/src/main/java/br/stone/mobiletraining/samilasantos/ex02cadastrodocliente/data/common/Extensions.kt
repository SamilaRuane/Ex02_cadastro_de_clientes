package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.common

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Calendar? = try {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    val calendar = Calendar.getInstance()
    calendar.time = simpleDateFormat.parse(this)
    calendar
} catch (e: ParseException) {
    null
}

fun Calendar.parseToString(year: Int, monthOfYear: Int, dayOfMonth: Int): String {
    this.set(Calendar.YEAR, year)
    this.set(Calendar.MONTH, monthOfYear)
    this.set(Calendar.DAY_OF_MONTH, dayOfMonth)

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    return simpleDateFormat.format(this.time)
}

fun Calendar.parseToString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    return simpleDateFormat.format(this.time)
}


fun Activity.showFeedback(message: String, listener: (DialogInterface, Int) -> Unit) {
    val build = AlertDialog.Builder(this)
    build.setMessage(message)
    build.setCancelable(false)
    build.setPositiveButton(getString(R.string.ok_label), listener)
    build.show()
}

fun Long.toDate(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return calendar
}
