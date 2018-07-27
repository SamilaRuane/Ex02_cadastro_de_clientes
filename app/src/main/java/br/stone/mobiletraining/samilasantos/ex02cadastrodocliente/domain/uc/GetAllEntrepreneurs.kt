package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException

class GetAllEntrepreneurs(private val repository: EntrepreneurRepository) {

    fun execute(): List<Entrepreneur> = try {
        repository.findAll()
    } catch (e: UnavailableRepositoryException) {
        ArrayList()
    } catch (e : RepositoryNotFoundException) {
        ArrayList()
    }

}