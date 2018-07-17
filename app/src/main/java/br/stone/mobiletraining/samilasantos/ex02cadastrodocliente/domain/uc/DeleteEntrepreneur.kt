package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException

class DeleteEntrepreneur(private val repository: Repository) {

    companion object {
        private const val ERROR_FEEDBACK = "Ocorreu um erro ao deletar usuário"
    }

    fun execute(entity: Entrepreneur) : Result = try {
        repository.delete(entity)
        Result (Result.SUCCESS, "")
    } catch (e: UnavailableRepositoryException) {
        Result(Result.ERROR,
                if (e.message != null && e.message.isNotEmpty()) e.message
                else DeleteEntrepreneur.ERROR_FEEDBACK)
    } catch (e: RepositoryNotFoundException) {
        Result(Result.ERROR,
                if (e.message != null && e.message.isNotEmpty()) e.message
                else DeleteEntrepreneur.ERROR_FEEDBACK)
    }

}