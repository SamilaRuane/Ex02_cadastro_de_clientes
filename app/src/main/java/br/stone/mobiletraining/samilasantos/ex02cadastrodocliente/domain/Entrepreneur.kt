package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain

import java.util.*

data class Entrepreneur(val fullName: String,
                        val email: String,
                        val phone: Long,
                        val tradeName: String,
                        val birthDate: Date,
                        val individualEntrepreneur: Boolean) {

    fun isValid() = !this.fullName.isEmpty()
            && !this.email.isEmpty()
            && !this.tradeName.isEmpty()
            && isPhoneValid()
            && isBirthDateValid()

    private fun isPhoneValid() = this.phone > 11111111
            || this.phone > 11111111111

    private fun isBirthDateValid() = !birthDate.before(Date())
}