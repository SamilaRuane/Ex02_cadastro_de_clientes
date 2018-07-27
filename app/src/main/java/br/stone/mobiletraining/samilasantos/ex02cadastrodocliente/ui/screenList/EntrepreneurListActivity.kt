package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.App
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.showFeedback
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoActivity
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterActivity
import kotlinx.android.synthetic.main.activity_entrepreneur_list.*

class EntrepreneurListActivity : AppCompatActivity() {

    private lateinit var viewModel: EntrepreneurListViewModel

    companion object {
        const val EXTRA_ENTREPRENEUR = "ENTREPRENEUR"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrepreneur_list)
        viewModel = (application as App).injector.entrepreneurListVMInstance()
        viewModel.subscribe(observer)

        setupView()
    }

    override fun onResume() {
        viewModel.getAllEntrepreneurs()
        super.onResume()
    }

    private val observer = object : EntrepreneurListContract.ViewStateObserver {
        override fun onResult(viewState: EntrepreneurListContract.ViewState) {
            when (viewState) {
                is EntrepreneurListContract.ViewState.Items -> {
                    if (!viewState.list.isEmpty()) {
                        text_empty_view.visibility = View.GONE
                        recycler_main_entrepreneurs.visibility = View.VISIBLE
                        recycler_main_entrepreneurs.layoutManager =
                                LinearLayoutManager(
                                        this@EntrepreneurListActivity,
                                        LinearLayoutManager.VERTICAL,
                                        false
                                )
                        recycler_main_entrepreneurs.adapter =
                                EntrepreneurAdapter(
                                        viewState.list,
                                        this@EntrepreneurListActivity::goToInfoScreen
                                )
                    } else {
                        text_empty_view.visibility = View.VISIBLE
                        recycler_main_entrepreneurs.visibility = View.GONE
                    }
                }
                is EntrepreneurListContract.ViewState.Error -> showFeedback(getString(R.string.error_feedback), { dialog, _ -> dialog.dismiss() })
            }
        }
    }

    private fun setupView() {
        setupActionbar()
        button_add.setOnClickListener { startActivity(Intent(this, RegisterActivity::class.java)) }
    }

    private fun setupActionbar() {
        supportActionBar?.title = getString(R.string.list_screen_label)
    }

    override fun onDestroy() {
        viewModel.unsubscribe(observer)
        super.onDestroy()
    }

    private fun goToInfoScreen(entrepreneurId: Long) {
        val intent = Intent(this, EntrepreneurInfoActivity::class.java)
        intent.putExtra(EXTRA_ENTREPRENEUR, entrepreneurId)
        startActivity(intent)
    }
}
