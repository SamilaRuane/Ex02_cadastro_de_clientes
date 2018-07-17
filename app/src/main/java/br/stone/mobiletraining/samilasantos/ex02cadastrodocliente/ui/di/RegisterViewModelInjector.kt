package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterViewModel

class RegisterViewModelInjector {
    fun inject(): RegisterViewModel = RegisterViewModel(RepositoryInjector().inject())
}