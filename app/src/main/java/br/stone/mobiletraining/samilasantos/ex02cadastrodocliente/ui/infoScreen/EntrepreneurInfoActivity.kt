package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import kotlinx.android.synthetic.main.activity_entrepreneur_info.*

class EntrepreneurInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrepreneur_info)
        setupView ()
    }

    private fun setupView () {
        setupActionBar ()

        button_delete.setOnClickListener {  }
    }

    private fun setupActionBar () {
        supportActionBar?.title = getString(R.string.info_screen_label)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
