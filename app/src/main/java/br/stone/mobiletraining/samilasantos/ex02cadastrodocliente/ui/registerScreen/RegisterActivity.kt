package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.RegisterViewModelInjector
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity(), RegisterContract.ViewStateObserver {

    private val date: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        edit_birth_date.text = SpannableStringBuilder(viewModel.processDate(year, monthOfYear, dayOfMonth))
    }
    private val viewModel: RegisterViewModel = RegisterViewModelInjector().inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel.subscribe(this)
        setupView()
    }

    private fun setupView() {
        setupActionBar()
        button_confirm.setOnClickListener {
            val entrepreneur = RegisterContract.ViewState.Item.EntrepreneurInfo(
                    edit_full_name.text.toString(),
                    edit_email.text.toString(),
                    edit_phone.text.toString(),
                    edit_trade_name.text.toString(),
                    edit_birth_date.text.toString(),
                    radio_group_individual_entrepreneur.checkedRadioButtonId == R.id.radio_button_yes
            )
            viewModel.handleConfirmAction(entrepreneur)
        }

        button_confirm.isEnabled = true

        edit_birth_date.setOnClickListener {
            DatePickerDialog(this,
                    date,
                    viewModel.calendar.get(Calendar.YEAR),
                    viewModel.calendar.get(Calendar.MONTH),
                    viewModel.calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setupActionBar() {
        supportActionBar?.title = "Criar Cadastro"
    }

    override fun onChange(viewState: RegisterContract.ViewState.GeneralState) {
        when(viewState){
            is RegisterContract.ViewState.GeneralState.Success -> displayFeedback(getString(R.string.success_feedback))
            is RegisterContract.ViewState.GeneralState.Error -> displayFeedback(viewState.feedback)
            is RegisterContract.ViewState.GeneralState.Loading -> displayFeedback("Carregando") //TODO trocar por um progress bar
            is RegisterContract.ViewState.GeneralState.ConfirmButton -> {manageButtonState(viewState.buttonState)}
        }
    }

    private fun displayFeedback (message : String){
        val build = AlertDialog.Builder(this)
        build.setMessage(message)
        build.setPositiveButton("Ok", { dialog, which -> dialog.dismiss() })
        build.show()
    }

    private fun manageButtonState (state : RegisterContract.ViewState.ButtonState) {
        when(state){
            is RegisterContract.ViewState.ButtonState.Enabled -> button_confirm.isEnabled = true
            is RegisterContract.ViewState.ButtonState.Disabled -> button_confirm.isEnabled = false
        }
    }

    override fun onDestroy() {
        viewModel.unsubscribe(this)
        super.onDestroy()
    }
}
