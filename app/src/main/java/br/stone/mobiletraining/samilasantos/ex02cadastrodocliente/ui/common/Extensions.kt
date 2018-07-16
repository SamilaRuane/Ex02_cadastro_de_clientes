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