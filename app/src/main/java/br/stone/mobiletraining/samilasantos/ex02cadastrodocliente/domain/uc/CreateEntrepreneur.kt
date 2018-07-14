package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException

class CreateEntrepreneur(private val repository: Repository) {

    companion object {
        private const val ERROR_FEEDBACK = "Ocorreu um erro ao salvar usuário"
        private const val INVALID_USER_FEEDBACK = "Por favor preencha todos os campos com informações válidas"
    }

    fun execute(entity: Entrepreneur): Result = try {
        if (!entity.isValid()) Result(Result.ERROR, INVALID_USER_FEEDBACK)
        else {
            repository.create(entity)
            Result(Result.SUCCESS, "")
        }
    } catch (e: UnavailableRepositoryException) {
        Result(Result.ERROR,
                if (e.message != null && e.message.isNotEmpty()) e.message
                else CreateEntrepreneur.ERROR_FEEDBACK)
    } catch (e : RepositoryNotFoundException) {
       Result(Result.ERROR,
               if (e.message != null && e.message.isNotEmpty()) e.message
               else CreateEntrepreneur.ERROR_FEEDBACK)
    }
}