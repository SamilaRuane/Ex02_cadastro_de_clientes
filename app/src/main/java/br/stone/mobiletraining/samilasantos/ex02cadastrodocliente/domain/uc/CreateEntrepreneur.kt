package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode

class CreateEntrepreneur(private val repository: EntrepreneurRepository) {

    fun execute(entity: Entrepreneur): Result = try {
        if (!entity.isValid()) Result.Error(ErrorCode.INCORRECT_INFO)
        else {
            repository.create(entity)
            Result.Success
        }
    } catch (e: UnavailableRepositoryException) {
        Result.Error(ErrorCode.SAVING_ERROR)
    } catch (e: RepositoryNotFoundException) {
        Result.Error(ErrorCode.SAVING_ERROR)
    }
}