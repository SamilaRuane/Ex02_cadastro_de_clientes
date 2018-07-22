package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import java.util.*

class TestRepository : Repository {
    override fun create(entity: Entrepreneur): Boolean = true

    override fun delete(entity: Entrepreneur): Boolean = true

    override fun findAll(): List<Entrepreneur> = arrayListOf(
            Entrepreneur("Empresário A", "empresarioonA@gmail.com", 2122222222, "Empresa A", Date(), true),
            Entrepreneur("Empresário B", "empresarioB@gmail.com", 2122222222, "Empresa B", Date(), true),
            Entrepreneur("Empresário C", "empresarioC@gmail.com", 2122222222, "Empresa C", Date(), true))
}

class EmptyRepository : Repository {
    override fun create(entity: Entrepreneur): Boolean = true

    override fun delete(entity: Entrepreneur): Boolean = true

    override fun findAll(): List<Entrepreneur> = arrayListOf()
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
