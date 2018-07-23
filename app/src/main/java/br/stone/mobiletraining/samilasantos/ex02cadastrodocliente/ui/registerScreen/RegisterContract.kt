package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode

interface RegisterContract {
    /**
     * States:
     *
     * SUCCESS
     * ERROR (feedback)
     * LOADING
     *
     * */

    sealed class ViewState {
        data class EntrepreneurInfo(val fullName: String = "",
                                    val email: String = "",
                                    val phone: String = "",
                                    val tradeName: String = "",
                                    val birthDate: String = "",
                                    val individualEntrepreneur: Boolean = false)


        sealed class GeneralState {
            object Success : GeneralState()
            data class Error(val code: ErrorCode) : GeneralState()
            object Loading : GeneralState()
            data class ConfirmButton(val buttonState: ButtonState) : GeneralState()
            data class ItemState(val entrepreneurInfo: EntrepreneurInfo) : GeneralState()
        }

        sealed class ButtonState {
            object Enabled : ButtonState()
            object Disabled : ButtonState()
        }
    }

    interface ViewStateObserver {
        fun onChange(viewState: ViewState.GeneralState)
    }
}