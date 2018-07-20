package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui

import android.app.Application
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.SharedPreferencesRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphBuilder
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphConfigurator
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.Mode

class App : Application(){
    val injector: GraphConfigurator =  GraphConfigurator.getInstance()

    init {
        injector.mode = Mode.App
    }

    override fun onCreate() {
        injector.mode = Mode.Test (GraphBuilder.builder().override().repository(SharedPreferencesRepository(this)).build()!!)
        super.onCreate()
    }
}