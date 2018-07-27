package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.showFeedback
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.App
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorMapper
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.diInject
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoContract.ViewState
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList.EntrepreneurListActivity
import kotlinx.android.synthetic.main.activity_entrepreneur_info.*

class EntrepreneurInfoActivity : AppCompatActivity(), ViewState.EntrepreneurInfoViewModelObserver {

    private val viewModel by diInject<EntrepreneurInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrepreneur_info)
        viewModel.subscribe(this)
        setupView()
    }

    private fun setupView() {
        setupActionBar()
        val entrepreneurId = intent.extras.getLong(EntrepreneurListActivity.EXTRA_ENTREPRENEUR)
        viewModel.recoveryUserInformation(entrepreneurId)

        button_delete.setOnClickListener { viewModel.handleDeleteButtonClicked() }
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

    override fun onChange(viewState: ViewState.GeneralState) {
        when (viewState) {
            is ViewState.GeneralState.Success -> showFeedback(getString(R.string.success_feedback),
                    { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    })
            is ViewState.GeneralState.Error -> showFeedback(ErrorMapper(this).getFeedbackMessage(viewState.code),
                    { dialog, _ -> dialog.dismiss() })

            is ViewState.GeneralState.ItemState -> fillInfo(viewState.entrepreneurInfo)

        }
    }

    private fun fillInfo(entrepreneur: EntrepreneurInfoContract.EntrepreneurInfo) {
        text_owner_name.text = entrepreneur.fullName
        text_email.text = entrepreneur.email
        text_phone.text = entrepreneur.phone
        text_birth_date.text = entrepreneur.birthDate
        text_trade_name.text = entrepreneur.tradeName
        text_individual_entrepreneur.text = if (entrepreneur.individualEntrepreneur) getString(R.string.yes_label) else getString(R.string.no_label)
    }

    override fun onStop() {
        viewModel.unsubscribe(this)
        super.onStop()
    }
}
