package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain

import java.io.Serializable
import java.util.*

data class Entrepreneur(val id : Long = 0,
                        val fullName: String,
                        val email: String,
                        val phone: Long,
                        val tradeName: String,
                        val birthDate: Calendar,
                        val individualEntrepreneur: Boolean) : Serializable {

    fun isValid() = !this.fullName.isEmpty()
            && !this.email.isEmpty()
            && !this.tradeName.isEmpty()
            && isPhoneValid()
            && isBirthDateValid()

    private fun isPhoneValid() = this.phone > 11111111
            || this.phone > 11111111111

    private fun isBirthDateValid() = (birthDate.before(Calendar.getInstance())
            || birthDate == Calendar.getInstance())

    override fun equals(other: Any?): Boolean {
        return this.fullName == (other as Entrepreneur).fullName
    }
}