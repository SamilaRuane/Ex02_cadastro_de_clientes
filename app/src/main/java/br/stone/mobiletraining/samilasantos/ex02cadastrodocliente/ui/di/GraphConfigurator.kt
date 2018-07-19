package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.App
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList.EntrepreneurListViewModel
import com.github.salomonbrys.kodein.*
import java.util.*

val diModule = Kodein.Module {
    bind<Repository>() with singleton {
        InMemoryRepository()
    }

    bind<EntrepreneurListViewModel>() with provider {
        EntrepreneurListViewModel(instance())
    }

    bind<EntrepreneurInfoViewModel>() with provider {
        EntrepreneurInfoViewModel(instance())
    }

    bind<RegisterViewModel>() with provider {
        RegisterViewModel(instance())
    }
}

val appContainer = Kodein {
    import(diModule)
}

val testContainer = Kodein {
    import(diModule)
    bind<Repository>(overrides = true) with provider {
        TestRepository()
    }
}

class GraphConfigurator(private val whoInstancedMe: Any) {
    companion object {
        private var configurator: GraphConfigurator? = null

        fun getInstance(whoCalls: Any): GraphConfigurator {
            if (configurator == null) configurator = GraphConfigurator(whoCalls)

            return configurator as GraphConfigurator
        }
    }

    fun entrepreneurInfoVMInstance(): EntrepreneurInfoViewModel = when (whoInstancedMe) {
        is App -> appContainer.instance()
        else -> testContainer.instance()
    }

    fun entrepreneurListVMInstance(): EntrepreneurListViewModel = when (whoInstancedMe) {
        is App -> appContainer.instance()
        else -> testContainer.instance()
    }

    fun registerVMInstance(): RegisterViewModel = when (whoInstancedMe) {
        is App -> appContainer.instance()
        else -> testContainer.instance()
    }
}

class TestRepository : Repository {
    override fun create(entity: Entrepreneur): Boolean = true

    override fun delete(entity: Entrepreneur): Boolean = true

    override fun findAll(): List<Entrepreneur> = arrayListOf(
            Entrepreneur("Empresário A", "empresarioonA@gmail.com", 2122222222, "Empresa A", Date(), true),
            Entrepreneur("Empresário B", "empresarioB@gmail.com", 2122222222, "Empresa B", Date(), true),
            Entrepreneur("Empresário C", "empresarioC@gmail.com", 2122222222, "Empresa C", Date(), true))
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
