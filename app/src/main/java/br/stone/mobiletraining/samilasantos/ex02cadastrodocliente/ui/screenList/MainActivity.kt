package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc.GetAllEntrepreneurs
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.RepositoryInjector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        setupActionbar()
        recycler_main_entrepreneurs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_main_entrepreneurs.adapter = EntrepreneursAdapter(GetAllEntrepreneurs(RepositoryInjector().inject()).execute())
    }

    private fun setupActionbar (){
        supportActionBar?.title = "CLIENTES"
    }
}
