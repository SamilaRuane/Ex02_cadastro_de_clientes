package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode

class EntrepreneurInfoContract {

    data class EntrepreneurInfo (val fullName: String = "",
                                 val email: String = "",
                                 val phone: String = "",
                                 val tradeName: String = "",
                                 val birthDate: String = "",
                                 val individualEntrepreneur: Boolean = false)

    sealed class ViewState {
        sealed class GeneralState {
            object Success : GeneralState()
            data class Error (val code : ErrorCode): GeneralState()
            data class ItemState (val entrepreneurInfo : EntrepreneurInfo) : GeneralState ()
        }

        interface EntrepreneurInfoViewModelObserver {
            fun onChange(viewState: EntrepreneurInfoContract.ViewState.GeneralState)
        }
    }
}