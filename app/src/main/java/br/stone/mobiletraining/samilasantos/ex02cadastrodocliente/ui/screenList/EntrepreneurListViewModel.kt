package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.GetAllEntrepreneurs
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.GetCompanyInitials

class EntrepreneurListViewModel(private val repository: EntrepreneurRepository) {

    private val observers = ArrayList<EntrepreneurListContract.ViewStateObserver>()

    fun getAllEntrepreneurs() {
        val items = GetAllEntrepreneurs(repository).execute()
                .map {
                    EntrepreneurListContract.EntrepreneurItem(
                            id = it.id,
                            initials = GetCompanyInitials.process(it.tradeName),
                            entrepreneurName = it.fullName,
                            tradeName = it.tradeName
                    )
                }
        update(EntrepreneurListContract.ViewState.Items(items))
    }

    fun subscribe(observer: EntrepreneurListContract.ViewStateObserver) {
        observers.add(observer)
    }

    fun unsubscribe(observer: EntrepreneurListContract.ViewStateObserver) {
        observers.remove(observer)
    }

    private fun update(viewState: EntrepreneurListContract.ViewState) {
        for (observer in observers) {
            observer.onResult(viewState)
        }
    }
}