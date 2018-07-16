package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen

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
        sealed class Item {
            data class EntrepreneurInfo(val fullName: String,
                                        val email: String,
                                        val phone: String,
                                        val tradeName: String,
                                        val birthDate: String,
                                        val individualEntrepreneur: Boolean) : Item()
        }

        sealed class GeneralState {
            object Success : GeneralState()
            data class Error(val feedback: String) : GeneralState()
            object Loading : GeneralState()
            data class ConfirmButton ( val buttonState : ButtonState ) : GeneralState ()
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