package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException

class GetAllEntrepreneurs(private val repository: Repository) {

    fun execute(): List<Entrepreneur> = try {
        repository.findAll()
    } catch (e: UnavailableRepositoryException) {
        ArrayList()
    } catch (e : RepositoryNotFoundException) {
        ArrayList()
    }

}