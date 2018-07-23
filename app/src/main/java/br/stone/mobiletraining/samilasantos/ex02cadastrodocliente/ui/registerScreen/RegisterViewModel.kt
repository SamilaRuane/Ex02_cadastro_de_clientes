package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.common.toDate
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.CreateEntrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode

class RegisterViewModel(private val repository: Repository) {

    private val viewStateObservers = ArrayList<RegisterContract.ViewStateObserver>()
    private var entrepreneurInfo: RegisterContract.ViewState.EntrepreneurInfo? = null

    fun handleConfirmAction(fullName: String,
                            email: String,
                            phone: String,
                            tradeName: String,
                            birthDate: String,
                            individualEntrepreneur: Boolean) {

        entrepreneurInfo = RegisterContract.ViewState.EntrepreneurInfo(fullName = fullName,
                email = email,
                phone = phone,
                tradeName = tradeName,
                birthDate = birthDate,
                individualEntrepreneur = individualEntrepreneur)

        if (entrepreneurInfo != null && checkIfAllFieldAreFilled(entrepreneurInfo!!)) {
            if (birthDate.toDate() != null) {
                update(RegisterContract.ViewState.GeneralState.Loading)
                val result = CreateEntrepreneur(repository).execute(Entrepreneur(0, fullName,
                        email, if (!phone.isEmpty()) phone.toLong() else 0,
                        tradeName, birthDate.toDate()!!, individualEntrepreneur))

                when (result) {

                    is Result.Success -> update(RegisterContract.ViewState.GeneralState.Success)
                    is Result.Error -> update(RegisterContract.ViewState.GeneralState.Error(result.code))
                }
            } else {
                update(RegisterContract.ViewState.GeneralState.Error(ErrorCode.INVALID_DATE))
            }
        } else {
            update(RegisterContract.ViewState.GeneralState.ConfirmButton(RegisterContract.ViewState.ButtonState.Disabled))
        }
    }

    fun handleOnResume() {
       if(entrepreneurInfo != null) update(RegisterContract.ViewState.GeneralState.ItemState(entrepreneurInfo = entrepreneurInfo!!))
    }

    private fun checkIfAllFieldAreFilled(entrepreneurInfo: RegisterContract.ViewState.EntrepreneurInfo): Boolean {
        return (!entrepreneurInfo.fullName.isEmpty() && !entrepreneurInfo.email.isEmpty()
                && !entrepreneurInfo.phone.isEmpty() && !entrepreneurInfo.tradeName.isEmpty()
                && !entrepreneurInfo.birthDate.isEmpty())
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