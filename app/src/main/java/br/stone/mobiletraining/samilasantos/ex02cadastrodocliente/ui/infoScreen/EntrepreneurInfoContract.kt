package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode

class EntrepreneurInfoContract {

    sealed class ViewState {
        sealed class GeneralState {
            object Success : GeneralState()
            data class Error (val code : ErrorCode): GeneralState()
        }

        interface EntrepreneurInfoViewModelObserver {
            fun onChange(viewState: EntrepreneurInfoContract.ViewState.GeneralState)
        }
    }
}