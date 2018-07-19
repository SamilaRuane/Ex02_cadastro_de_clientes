package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList.EntrepreneurListViewModel
import java.util.*

class RegisterViewModelInjector (private val repositoryInstance : Repository) {
    fun inject(): RegisterViewModel = RegisterViewModel(repositoryInstance)
}

class EntrepreneurListViewModelInjector (private val repositoryInstance : Repository) {
    fun inject (): EntrepreneurListViewModel = EntrepreneurListViewModel(repositoryInstance)
}

class EntrepreneurInfoViewModelInjector(private val repositoryInstance : Repository) {
    fun inject() = EntrepreneurInfoViewModel(repositoryInstance)
}

class RepositoryInjector  {
    fun inject () : Repository = InMemoryRepository()
}

class InMemoryRepository : Repository {
    override fun create(entity: Entrepreneur): Boolean = true

    override fun delete(entity: Entrepreneur): Boolean = true

    override fun findAll(): List<Entrepreneur> = arrayListOf(
            Entrepreneur("Empresário 1", "empresarioone@gmail.com", 2122222222, "Empresa 1", Date(), true),
            Entrepreneur("Empresário 2", "empresariotwo@gmail.com", 2122222222, "Empresa 2", Date(), true),
            Entrepreneur("Empresário 3", "empresariothree@gmail.com", 2122222222, "Empresa 3", Date(), true),
            Entrepreneur("Empresário 4", "empresariofour@gmail.com", 2122222222, "Empresa 4", Date(), true),
            Entrepreneur("Empresário 5", "empresariofive@gmail.com", 2122222222, "Empresa 5", Date(), true),
            Entrepreneur("Empresário 6", "empresariosix@gmail.com", 2122222222, "Empresa 6", Date(), true),
            Entrepreneur("Empresário 7", "empresarioseven@gmail.com", 2122222222, "Empresa 7", Date(), true))
}