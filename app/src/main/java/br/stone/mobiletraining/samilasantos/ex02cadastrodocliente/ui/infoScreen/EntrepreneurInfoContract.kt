package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

class EntrepreneurInfoContract {

    sealed class ViewState {
        sealed class GeneralState {
            object Success : GeneralState()
            object Error : GeneralState()
        }

        interface EntrepreneurInfoViewModelObserver {
            fun onChange(viewState: EntrepreneurInfoContract.ViewState.GeneralState)
        }
    }
}