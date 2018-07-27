package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate () : Date? = try {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    simpleDateFormat.parse(this)
}catch (e : ParseException){
    null
}

fun Date.parseToString () : String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    return simpleDateFormat.format(this)
}

fun Calendar.parseToString (year : Int, monthOfYear : Int, dayOfMonth : Int) : String {
    this.set(Calendar.YEAR, year)
    this.set(Calendar.MONTH, monthOfYear)
    this.set(Calendar.DAY_OF_MONTH, dayOfMonth)

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    return simpleDateFormat.format(this.time)
}

fun Activity.showFeedback (message : String, listener: (DialogInterface, Int) -> Unit) {
    val build = AlertDialog.Builder(this)
    build.setMessage(message)
    build.setCancelable(false)
    build.setPositiveButton(getString(R.string.ok_label), listener)
    build.show()
}

fun Long.toDate () : Date = Date (this)

