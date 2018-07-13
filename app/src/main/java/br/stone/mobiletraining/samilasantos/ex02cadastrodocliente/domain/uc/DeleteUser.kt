package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Interactor
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException

class DeleteUser(private val repository: Repository, private val interactor: Interactor) {

    companion object {
        private const val ERROR = "Ocorreu um erro ao deletar usuário"
    }

    fun delete(entity: Entrepreneur) = try {
        repository.delete(entity)
    } catch (e: UnavailableRepositoryException) {
        interactor.onError(if (e.message?.isNotEmpty()!!) e.message else DeleteUser.ERROR)
        false
    } catch (e: RepositoryNotFoundException) {
        interactor.onError(if (e.message?.isNotEmpty()!!) e.message else DeleteUser.ERROR)
        false
    }

}