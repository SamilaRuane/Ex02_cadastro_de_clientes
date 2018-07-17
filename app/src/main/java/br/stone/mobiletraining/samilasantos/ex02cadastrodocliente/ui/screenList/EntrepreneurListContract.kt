package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur

interface EntrepreneurListContract {

    sealed class ViewState {
        data class Items (val list: List<Entrepreneur>) : ViewState()
        object Error : ViewState()
    }

    interface ViewStateObserver {
        fun onResult (viewState : ViewState)
    }
}