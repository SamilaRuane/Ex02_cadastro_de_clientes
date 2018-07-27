package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui

import android.app.Application
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.Injector

class App : Application() {
    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        injector = Injector(this)
    }
}