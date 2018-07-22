package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui

import android.app.Application
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphConfigurator
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.Mode

class App : Application(){
    lateinit var injector: GraphConfigurator

    override fun onCreate() {
        super.onCreate()
        injector =  GraphConfigurator.getInstance(this)
        injector.mode = Mode.App
    }
}