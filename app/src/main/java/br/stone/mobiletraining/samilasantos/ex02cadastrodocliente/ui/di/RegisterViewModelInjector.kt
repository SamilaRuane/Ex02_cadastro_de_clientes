package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterViewModel
import java.util.*

class RegisterViewModelInjector {
    fun inject(): RegisterViewModel = RegisterViewModel(Calendar.getInstance(), RepositoryInjector().inject())
}