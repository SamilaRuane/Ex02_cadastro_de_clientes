package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.TestRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphBuilder
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphConfigurator
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.Mode

open class UiTest {
    protected fun defineTestRepository(graphConfigurator: GraphConfigurator) {
       graphConfigurator.mode = Mode.Test(GraphBuilder.builder()
                .override()
                .repository(TestRepository())
                .build()!!)
    }
}