package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.parseToString
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.DeleteEntrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.GetAllEntrepreneurs

class EntrepreneurInfoViewModel(val repository: EntrepreneurRepository) {

    private val observers = ArrayList<EntrepreneurInfoContract.ViewState.EntrepreneurInfoViewModelObserver>()
    private var entrepreneur: Entrepreneur? = null

    fun handleDeleteButtonClicked() {
        if (entrepreneur != null) {
            val result = DeleteEntrepreneur(repository).execute(entrepreneur!!)
            when (result) {
                is Result.Success -> update(EntrepreneurInfoContract.ViewState.GeneralState.Success)
                is Result.Error -> update(EntrepreneurInfoContract.ViewState.GeneralState.Error(result.code))
            }
        }
    }

    fun recoveryUserInformation(id: Long) {
        val entrepreneurs = GetAllEntrepreneurs(repository).execute()
        entrepreneur = try { entrepreneurs.single { id == it.id } } catch (_: Throwable) { null }

        entrepreneur?.let { e ->
            val entrepreneurInfo = EntrepreneurInfoContract
                    .EntrepreneurInfo(e.fullName, e.email, e.phone.toString(), e.tradeName,
                            e.birthDate.parseToString(), e.individualEntrepreneur)

            update(EntrepreneurInfoContract.ViewState.GeneralState.ItemState(entrepreneurInfo))
        }
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