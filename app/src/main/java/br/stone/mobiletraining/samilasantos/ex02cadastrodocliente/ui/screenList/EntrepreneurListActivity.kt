package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.EntrepreneurListViewModelInjector
import kotlinx.android.synthetic.main.activity_main.*

class EntrepreneurListActivity : AppCompatActivity(), EntrepreneurListContract.ViewStateObserver {

    private val viewModel = EntrepreneurListViewModelInjector().inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.subscribe(this)
        setupView()
    }

    private fun setupView() {
        setupActionbar()
        viewModel.getAllEntrepreneurs()
        recycler_main_entrepreneurs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
       // recycler_main_entrepreneurs.adapter = EntrepreneurAdapter(ArrayList())
    }

    private fun setupActionbar() {
        supportActionBar?.title = "CLIENTES"
    }

    override fun onResult(viewState: EntrepreneurListContract.ViewState) {
        when (viewState) {
            is EntrepreneurListContract.ViewState.Items -> recycler_main_entrepreneurs.adapter = EntrepreneurAdapter(viewState.list)
        }
    }

    override fun onDestroy() {
        viewModel.unsubscribe(this)
        super.onDestroy()
    }
}
