package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import android.content.Context
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.entrepreneurs.SharedPreferencesEntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList.EntrepreneurListViewModel
import com.github.salomonbrys.kodein.*

data class Graph(var repository: EntrepreneurRepository? = null,
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

    fun repository(instance: EntrepreneurRepository): GraphBuilder {
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

class GraphConfigurator private constructor(val context: Context) {
    companion object {
        private var configurator: GraphConfigurator? = null

        fun getInstance(context: Context): GraphConfigurator {
            if (configurator == null) configurator = GraphConfigurator(context)

            return configurator as GraphConfigurator
        }
    }

    private val diModule = Kodein.Module {
        bind<EntrepreneurRepository>() with singleton {
            SharedPreferencesEntrepreneurRepository(instance())
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

        bind<Context>() with singleton {
            context
        }
    }

    private val appContainer = Kodein {
        import(diModule)
    }

    var mode: Mode = Mode.App

    fun entrepreneurInfoVMInstance(): EntrepreneurInfoViewModel = when (mode) {
        is Mode.App -> appContainer.instance()
        is Mode.Test -> reconfigureContainer((mode as Mode.Test).graph, context).instance()
    }

    fun entrepreneurListVMInstance(): EntrepreneurListViewModel = when (mode) {
        is Mode.App -> appContainer.instance()
        is Mode.Test -> reconfigureContainer((mode as Mode.Test).graph, context).instance()
    }

    fun registerVMInstance(): RegisterViewModel = when (mode) {
        is Mode.App -> appContainer.instance()
        is Mode.Test -> reconfigureContainer((mode as Mode.Test).graph, context).instance()
    }

    private fun reconfigureContainer(graph: Graph, context: Context): Kodein = Kodein {
        import(diModule)
        if (graph.repository != null) {
            bind<EntrepreneurRepository>(overrides = true) with singleton {
                graph.repository as EntrepreneurRepository
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
}