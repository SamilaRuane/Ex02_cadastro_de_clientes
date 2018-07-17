package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result.Companion.SUCCESS
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.DeleteEntrepreneur

class EntrepreneurInfoViewModel(val repository: Repository) {

    private val observers = ArrayList<EntrepreneurInfoContract.ViewState.EntrepreneurInfoViewModelObserver>()

    fun handleDeleteButtonClicked(entrepreneur: Entrepreneur) {
        val result = DeleteEntrepreneur(repository).execute(entrepreneur)
        if (result.status == SUCCESS) update(EntrepreneurInfoContract.ViewState.GeneralState.Success)
        else update(EntrepreneurInfoContract.ViewState.GeneralState.Error)
    }

    fun subscribe(observer: EntrepreneurInfoContract.ViewState.EntrepreneurInfoViewModelObserver) {
        observers.add(observer)
    }

    fun unsubscribe(observer: EntrepreneurInfoContract.ViewState.EntrepreneurInfoViewModelObserver) {
        observers.remove(observer)
    }

    private fun update(viewState: EntrepreneurInfoContract.ViewState.GeneralState) {
        for (observer in observers) {
            observer.onChange(viewState)
        }
    }

}