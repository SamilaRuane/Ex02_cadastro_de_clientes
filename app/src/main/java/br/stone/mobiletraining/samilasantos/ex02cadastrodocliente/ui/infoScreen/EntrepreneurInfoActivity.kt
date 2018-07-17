package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.parseToString
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.showFeedback
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.EntrepreneurInfoViewModelInjector
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList.EntrepreneurListActivity
import kotlinx.android.synthetic.main.activity_entrepreneur_info.*

class EntrepreneurInfoActivity : AppCompatActivity(), EntrepreneurInfoContract.ViewState.EntrepreneurInfoViewModelObserver {

    private val viewModel = EntrepreneurInfoViewModelInjector().inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrepreneur_info)
        viewModel.subscribe(this)
        setupView()
    }

    private fun setupView() {
        setupActionBar()
        val entrepreneur = intent.extras.getSerializable(EntrepreneurListActivity.EXTRA_ENTREPRENEUR) as Entrepreneur
        fillInfo(entrepreneur)

        button_delete.setOnClickListener { viewModel.handleDeleteButtonClicked(entrepreneur) }
    }

    private fun setupActionBar() {
        supportActionBar?.title = getString(R.string.info_screen_label)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onChange(viewState: EntrepreneurInfoContract.ViewState.GeneralState) {
        when (viewState) {
            is EntrepreneurInfoContract.ViewState.GeneralState.Success -> showFeedback(getString(R.string.success_feedback),
                    { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    })
            is EntrepreneurInfoContract.ViewState.GeneralState.Error -> showFeedback(getString(R.string.error_feedback),
                    { dialog, _ -> dialog.dismiss() })
        }
    }

    private fun fillInfo(entrepreneur: Entrepreneur) {
        text_owner_name.text = entrepreneur.fullName
        text_email.text = entrepreneur.email
        text_phone.text = entrepreneur.phone.toString()
        text_birth_date.text = entrepreneur.birthDate.parseToString()
        text_trade_name.text = entrepreneur.tradeName
        text_individual_entrepreneur.text = if (entrepreneur.individualEntrepreneur) getString(R.string.yes_label) else getString(R.string.no_label)
    }

    override fun onStop() {
        viewModel.unsubscribe(this)
        super.onStop()
    }
}
