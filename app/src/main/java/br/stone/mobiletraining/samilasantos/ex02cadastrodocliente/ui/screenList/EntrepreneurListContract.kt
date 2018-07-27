package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

interface EntrepreneurListContract {

    data class EntrepreneurItem(
            val id: Long,
            val initials: String,
            val entrepreneurName: String,
            val tradeName: String
    )

    sealed class ViewState {
        data class Items (val list: List<EntrepreneurItem>) : ViewState()
        object Error : ViewState()
    }

    interface ViewStateObserver {
        fun onResult (viewState : ViewState)
    }
}