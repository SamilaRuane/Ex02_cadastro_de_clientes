package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
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


fun configureContainer(graph: Graph): Kodein = Kodein {
    import(diModule)
    if (graph.repository != null) {
        bind<Repository>(overrides = true) with singleton {
            graph.repository as Repository
        }
    }

    if (graph.infoViewModel != null) {
        bind<EntrepreneurInfoViewModel>(overrides = true) with provider {
            graph.infoViewModel as EntrepreneurInfoViewModel
        }
    }

    if (graph.listViewModel != null) {
        bind<EntrepreneurListViewModel>(overrides = true) with provider {
            graph.listViewModel as EntrepreneurListViewModel
        }
    }

    if (graph.registerViewModel != null) {
        bind<RegisterViewModel>(overrides = true) with provider {
            graph.registerViewModel as RegisterViewModel
        }
    }
}


data class Graph(var repository: Repository? = null,
                 var infoViewModel: EntrepreneurInfoViewModel? = null,
                 var registerViewModel: RegisterViewModel? = null,
                 var listViewModel: EntrepreneurListViewModel? = null)


class GraphBuilder(private val graph: Graph?) {

    companion object {
        fun builder() = GraphBuilder(Graph())
    }

    fun build(): Graph? {
        return graph
    }

    fun override() = GraphBuilder(graph)

    fun repository(instance: Repository): GraphBuilder {
        graph?.repository = instance
        return this
    }

    fun infoViewModel(instance: EntrepreneurInfoViewModel): GraphBuilder {
        graph?.infoViewModel = instance
        return this
    }

    fun registerViewModel(instance: RegisterViewModel): GraphBuilder {
        graph?.registerViewModel = instance
        return this
    }

    fun listViewModel(instance: EntrepreneurListViewModel): GraphBuilder {
        graph?.listViewModel = instance
        return this
    }
}

sealed class Mode {
    object App : Mode()
    data class Test(val graph: Graph) : Mode()
}

class GraphConfigurator(private val mode: Mode) {
    companion object {
        private var configurator: GraphConfigurator? = null

        fun getInstance(mode: Mode): GraphConfigurator {
            if (configurator == null) configurator = GraphConfigurator(mode)

            return configurator as GraphConfigurator
        }
    }

    fun entrepreneurInfoVMInstance(): EntrepreneurInfoViewModel = when (mode) {
        is Mode.App -> appContainer.instance()
        is Mode.Test -> configureContainer(mode.graph).instance()
    }

    fun entrepreneurListVMInstance(): EntrepreneurListViewModel = when (mode) {
        is Mode.App -> appContainer.instance()
        is Mode.Test -> configureContainer(mode.graph).instance()
    }

    fun registerVMInstance(): RegisterViewModel = when (mode) {
        is Mode.App -> appContainer.instance()
        is Mode.Test -> configureContainer(mode.graph).instance()
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
