package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphBuilder
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphConfigurator
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.Mode
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.TestRepository

open class UiTest {
    protected fun defineTestRepository() {
        GraphConfigurator.getInstance().mode = Mode.Test(GraphBuilder.builder()
                .override()
                .repository(TestRepository())
                .build()!!)
    }
}