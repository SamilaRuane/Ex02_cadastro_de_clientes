package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode

class DeleteEntrepreneur(private val repository: EntrepreneurRepository) {
    fun execute(entity: Entrepreneur): Result = try {
        repository.delete(entity)
        Result.Success
    } catch (e: UnavailableRepositoryException) {
        Result.Error(ErrorCode.DELETING_ERROR)
    } catch (e: RepositoryNotFoundException) {
        Result.Error(ErrorCode.DELETING_ERROR)
    }

}