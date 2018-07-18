package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate () : Date? = try {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    simpleDateFormat.parse(this)
}catch (e : ParseException){
    null
}

fun Calendar.parseToString (year : Int, monthOfYear : Int, dayOfMonth : Int) : String {
    this.set(Calendar.YEAR, year)
    this.set(Calendar.MONTH, monthOfYear)
    this.set(Calendar.DAY_OF_MONTH, dayOfMonth)

    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    return simpleDateFormat.format(this.time)
}

