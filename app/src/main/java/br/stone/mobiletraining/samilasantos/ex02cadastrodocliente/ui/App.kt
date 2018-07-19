package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui

import android.app.Application
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphConfigurator

class App : Application(){
    val injector: GraphConfigurator
            get () =  GraphConfigurator.getInstance(whoCalls = this)
}