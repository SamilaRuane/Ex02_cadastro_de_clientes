package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoViewModel

class EntrepreneurInfoViewModelInjector {
    fun inject () = EntrepreneurInfoViewModel (RepositoryInjector().inject())
}