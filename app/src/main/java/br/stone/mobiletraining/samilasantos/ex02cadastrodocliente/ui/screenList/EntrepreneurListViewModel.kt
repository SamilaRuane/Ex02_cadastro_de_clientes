package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.GetAllEntrepreneurs

class EntrepreneurListViewModel (private val repository: Repository) {

    private val observers = ArrayList <EntrepreneurListContract.ViewStateObserver> ()

    fun getAllEntrepreneurs () {
        val items = GetAllEntrepreneurs(repository).execute()
        update (EntrepreneurListContract.ViewState.Items (items))
    }

    fun subscribe (observer: EntrepreneurListContract.ViewStateObserver) {
        observers.add(observer)
    }

    fun unsubscribe (observer: EntrepreneurListContract.ViewStateObserver) {
        observers.remove(observer)
    }

    private fun update (viewState : EntrepreneurListContract.ViewState) {
        for (observer in observers){
            observer.onResult(viewState)
        }
    }
}