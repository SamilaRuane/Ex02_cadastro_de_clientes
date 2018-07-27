package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui

import android.support.test.InstrumentationRegistry
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton

object UiTest {
    fun defineTestRepository(entrepreneurRepository: EntrepreneurRepository) {
        val injector = (InstrumentationRegistry.getTargetContext().applicationContext as App).injector
        injector.reconfigureGraph {
            bind<EntrepreneurRepository>(overrides = true) with singleton { entrepreneurRepository }
        }
    }
}