package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di

import android.app.Activity
import android.app.Application
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.entrepreneurs.SharedPreferencesEntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.App
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterViewModel
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList.EntrepreneurListViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.conf.ConfigurableKodein

class Injector(private val application: Application) {

    fun reconfigureGraph(body: Kodein.Builder.() -> Unit) {
        dependencies = dependenciesBuilder()

        with(configurableGraph) {
            clear()
            addExtend(dependencies)
            addConfig(body)
        }
    }

    val configurableGraph  by lazy {
        ConfigurableKodein(mutable = true)
                .apply {
                    addExtend(dependencies)
                }
    }

    private val dependenciesBuilder = {
        Kodein {
            bind<EntrepreneurRepository>() with singleton {
                SharedPreferencesEntrepreneurRepository(instance<Application>())
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

            bind<Application>() with singleton { application }
        }
    }

    private var dependencies = dependenciesBuilder()
}

inline fun <reified T : Any> Activity.diInject(tag: Any? = null) =
        lazy {
            (application as App).injector.configurableGraph.instance<T>(tag)
        }
