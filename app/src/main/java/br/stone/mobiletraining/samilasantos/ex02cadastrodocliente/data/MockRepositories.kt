package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import java.util.*

class TestRepository : Repository {
    override fun create(entity: Entrepreneur): Boolean = true

    override fun delete(entity: Entrepreneur): Boolean = true

    override fun findAll(): List<Entrepreneur> = arrayListOf(
            Entrepreneur(0, "Empresário A", "empresarioonA@gmail.com", 2122222222, "Empresa A", Calendar.getInstance(), true),
            Entrepreneur(1, "Empresário B", "empresarioB@gmail.com", 2122222222, "Empresa B", Calendar.getInstance(), true),
            Entrepreneur(2, "Empresário C", "empresarioC@gmail.com", 2122222222, "Empresa C", Calendar.getInstance(), true))
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
            Entrepreneur(0, "Empresário 1", "empresarioone@gmail.com", 2122222222, "Empresa 1", Calendar.getInstance(), true),
            Entrepreneur(1, "Empresário 2", "empresariotwo@gmail.com", 2122222222, "Empresa 2", Calendar.getInstance(), true),
            Entrepreneur(2, "Empresário 3", "empresariothree@gmail.com", 2122222222, "Empresa 3", Calendar.getInstance(), true),
            Entrepreneur(3, "Empresário 4", "empresariofour@gmail.com", 2122222222, "Empresa 4", Calendar.getInstance(), true),
            Entrepreneur(4, "Empresário 5", "empresariofive@gmail.com", 2122222222, "Empresa 5", Calendar.getInstance(), true),
            Entrepreneur(5, "Empresário 6", "empresariosix@gmail.com", 2122222222, "Empresa 6", Calendar.getInstance(), true),
            Entrepreneur(6, "Empresário 7", "empresarioseven@gmail.com", 2122222222, "Empresa 7", Calendar.getInstance(), true))
}
