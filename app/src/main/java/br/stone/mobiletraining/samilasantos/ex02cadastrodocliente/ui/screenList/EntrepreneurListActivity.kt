package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.EntrepreneurListViewModelInjector
import kotlinx.android.synthetic.main.activity_entrepreneur_list.*
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterActivity

class EntrepreneurListActivity : AppCompatActivity(), EntrepreneurListContract.ViewStateObserver {

    private val viewModel = EntrepreneurListViewModelInjector().inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrepreneur_list)
        viewModel.subscribe(this)
        setupView()
    }

    private fun setupView() {
        setupActionbar()
        viewModel.getAllEntrepreneurs()

        button_add.setOnClickListener { startActivity(Intent(this, RegisterActivity :: class.java)) }
    }

    private fun setupActionbar() {
        supportActionBar?.title = "CLIENTES"
    }

    override fun onResult(viewState: EntrepreneurListContract.ViewState) {
        when (viewState) {
            is EntrepreneurListContract.ViewState.Items -> {
                if(!viewState.list.isEmpty()) {
                    text_empty_view.visibility = View.GONE
                    recycler_main_entrepreneurs.visibility = View.VISIBLE
                    recycler_main_entrepreneurs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    recycler_main_entrepreneurs.adapter = EntrepreneurAdapter(viewState.list)
                }else{
                    text_empty_view.visibility = View.VISIBLE
                    recycler_main_entrepreneurs.visibility = View.GONE
                }
            }
            is EntrepreneurListContract.ViewState.Error -> displayFeedback (getString(R.string.error_feedback))
        }
    }

    override fun onDestroy() {
        viewModel.unsubscribe(this)
        super.onDestroy()
    }

    private fun displayFeedback (message : String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("Ok", {dialog,_ -> dialog.dismiss()})
        builder.show()
    }
}
