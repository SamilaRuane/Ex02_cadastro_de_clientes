package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Interactors
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UserNotSavedException

class ManageUser(private val repository: Repository, private val interactor: Interactors) {

    companion object {
        private const val SAVING_ERROR = "Ocorreu um erro ao salvar usuário"
        private const val DELETING_ERROR = "Ocorreu um erro ao deletar usuário"
        private const val GETTING_ERROR = "Ocorreu um erro ao recuperar usuários"
    }

    fun create(entity: Entrepreneur) = try {
        repository.create(entity)
    } catch (e : UserNotSavedException) {
        e.let {
            interactor.onError(if (e.message?.isNotEmpty()!!) e.message else SAVING_ERROR)
        }
    } catch (e : UnavailableRepositoryException){
        e.let {
            interactor.onError(if (e.message?.isNotEmpty()!!) e.message else SAVING_ERROR)
        }
    }

    fun delete(entity: Entrepreneur) = try {
        repository.delete(entity)
    } catch (e: UnavailableRepositoryException) {
        e.let {
            interactor.onError(if (e.message?.isNotEmpty()!!) e.message else DELETING_ERROR)
        }
    }

    fun getAll(): List<Entrepreneur> = try {
        repository.findAll()
    } catch (e: UnavailableRepositoryException) {
        e.let {
            interactor.onError(if (e.message?.isNotEmpty()!!) e.message else GETTING_ERROR)
        }
        ArrayList()
    }
}