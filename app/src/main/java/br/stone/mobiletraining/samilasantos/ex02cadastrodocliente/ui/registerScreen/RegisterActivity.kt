package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.view.MenuItem
import android.view.View
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.parseToString
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.showFeedback
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.App
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorMapper
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.diInject
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity(), RegisterContract.ViewStateObserver {

    private val calendar = Calendar.getInstance()

    private val date: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        edit_birth_date.text = SpannableStringBuilder(calendar.parseToString(year, monthOfYear, dayOfMonth))
    }

    private val viewModel by diInject<RegisterViewModel>()

    private lateinit var entrepreneur: RegisterContract.ViewState.EntrepreneurInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel.subscribe(this)
        setupView()
    }

    override fun onResume() {
        viewModel.handleOnResume()
        super.onResume()
    }

    private fun setupView() {
        setupActionBar()

        edit_birth_date.setOnClickListener {
            DatePickerDialog(this,
                    date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        button_confirm.setOnClickListener {
            viewModel.handleConfirmAction(edit_full_name.text.toString(),
                    edit_email.text.toString(),
                    edit_phone.text.toString(),
                    edit_trade_name.text.toString(),
                    edit_birth_date.text.toString(),
                    radio_group_individual_entrepreneur.checkedRadioButtonId == R.id.radio_button_yes)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.title = getString(R.string.register_screen_label)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onChange(viewState: RegisterContract.ViewState.GeneralState) {
        when (viewState) {
            is RegisterContract.ViewState.GeneralState.Success -> {
                progress_dialog_layout.visibility = View.GONE
                showFeedback(getString(R.string.success_feedback), { dialog, _ ->
                    dialog.dismiss()
                    finish()
                })
            }
            is RegisterContract.ViewState.GeneralState.Error -> {
                progress_dialog_layout.visibility = View.GONE
                showFeedback(ErrorMapper(this).getFeedbackMessage(viewState.code), { dialog, _ -> dialog.dismiss() })
            }
            is RegisterContract.ViewState.GeneralState.Loading -> progress_dialog_layout.visibility = View.VISIBLE
            is RegisterContract.ViewState.GeneralState.ConfirmButton -> {
                manageButtonState(viewState.buttonState)
            }
            is RegisterContract.ViewState.GeneralState.ItemState -> {
                edit_full_name.text = SpannableStringBuilder(viewState.entrepreneurInfo.fullName)
                edit_email.text = SpannableStringBuilder(viewState.entrepreneurInfo.email)
                edit_phone.text = SpannableStringBuilder(viewState.entrepreneurInfo.phone)
                edit_trade_name.text = SpannableStringBuilder(viewState.entrepreneurInfo.tradeName)
                edit_birth_date.text = SpannableStringBuilder(viewState.entrepreneurInfo.birthDate)
                if (entrepreneur.individualEntrepreneur) radio_button_yes.isSelected = true else radio_button_no.isSelected = true
            }
        }
    }

    private fun manageButtonState(state: RegisterContract.ViewState.ButtonState) {
        when (state) {
            is RegisterContract.ViewState.ButtonState.Enabled -> {
            }
            is RegisterContract.ViewState.ButtonState.Disabled ->
                showFeedback(getString(R.string.fill_all_fields_feedback), { dialog, _ -> dialog.dismiss() })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        viewModel.unsubscribe(this)
        super.onDestroy()
    }
}
