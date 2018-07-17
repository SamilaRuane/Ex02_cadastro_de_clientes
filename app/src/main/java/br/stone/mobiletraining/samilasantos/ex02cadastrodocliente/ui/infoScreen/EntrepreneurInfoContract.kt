package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

class EntrepreneurInfoContract {

    sealed class ViewState {
        sealed class Item() {
            data class EntrepreneurInfo(
                    val fullName: String = "",
                    val email: String = "",
                    val phone: String = "",
                    val tradeName: String = "",
                    val birthDate: String = "",
                    val individualEntrepreneur: Boolean = false
            ) : Item ()
        }
    }
}