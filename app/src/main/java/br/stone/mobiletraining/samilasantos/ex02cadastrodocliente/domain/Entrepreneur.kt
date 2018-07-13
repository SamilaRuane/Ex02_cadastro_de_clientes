package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain

import java.util.*

data class Entrepreneur(private val fullName: String,
                        private val email: String,
                        private val phone: Long,
                        private val tradeName: String,
                        private val birthDate: Date,
                        private val individualEntrepreneur: Boolean) {

    fun isValid() = !this.fullName.isEmpty()
            && !this.email.isEmpty()
            && !this.tradeName.isEmpty()
            && isPhoneValid()
            && isBirthDateValid()

    private fun isPhoneValid() = this.phone > 11111111
            || this.phone > 11111111111

    private fun isBirthDateValid() = !birthDate.before(Date())
}