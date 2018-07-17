package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList.EntrepreneurListViewModel

class EntrepreneurListViewModelInjector {
    fun inject (): EntrepreneurListViewModel = EntrepreneurListViewModel(RepositoryInjector().inject())
}