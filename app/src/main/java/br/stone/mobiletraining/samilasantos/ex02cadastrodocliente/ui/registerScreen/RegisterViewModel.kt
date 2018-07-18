package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result.Companion.SUCCESS
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.CreateEntrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.toDate

class RegisterViewModel(private val repository: Repository) {

    private val viewStateObservers = ArrayList<RegisterContract.ViewStateObserver>()

    fun handleConfirmAction(item: RegisterContract.ViewState.Item.EntrepreneurInfo) {
        if (item.birthDate.toDate() != null) {
            update(RegisterContract.ViewState.GeneralState.Loading)
            val result = CreateEntrepreneur(repository).execute(Entrepreneur(item.fullName,
                    item.email, if (!item.phone.isEmpty()) item.phone.toLong() else 0,
                    item.tradeName, item.birthDate.toDate()!!, item.individualEntrepreneur))

            if (result.status == SUCCESS) update(RegisterContract.ViewState.GeneralState.Success)
            else update(RegisterContract.ViewState.GeneralState.Error(result.message))
        } else {
            update(RegisterContract.ViewState.GeneralState.Error("A data informada é inválida!"))
        }
    }

    fun handleOnFocusChange(item: RegisterContract.ViewState.Item.EntrepreneurInfo) {
        if (!item.fullName.isEmpty() && !item.email.isEmpty()
                && !item.phone.isEmpty() && !item.tradeName.isEmpty()
                && !item.birthDate.isEmpty()) {
            update(RegisterContract.ViewState.GeneralState.ConfirmButton(RegisterContract.ViewState.ButtonState.Enabled))
        } else {
            update(RegisterContract.ViewState.GeneralState.ConfirmButton(RegisterContract.ViewState.ButtonState.Disabled))
        }
    }

    fun subscribe(observer: RegisterContract.ViewStateObserver) {
        viewStateObservers.add(observer)
    }

    fun unsubscribe(observer: RegisterContract.ViewStateObserver) {
        viewStateObservers.remove(observer)
    }

    private fun update(viewState: RegisterContract.ViewState.GeneralState) {
        for (observer in viewStateObservers) {
            observer.onChange(viewState)
        }
    }
}