package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data

import android.content.Context
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import org.json.JSONObject
import java.util.*

class SharedPreferencesRepository(val context: Context) : Repository {

    private val sharedPreferences = context.getSharedPreferences(KEYS.SHARED_PREFERENCE_KEY.name, Context.MODE_PRIVATE)

    override fun create(entity: Entrepreneur): Boolean {
        val editor = sharedPreferences.edit()
        val entrepreneurInfoMap = hashMapOf(
                KEYS.ENTREPRENEUR_FULL_NAME.name to entity.fullName,
                KEYS.ENTREPRENEUR_EMAIL.name to entity.email,
                KEYS.ENTREPRENEUR_PHONE.name to entity.phone.toString(),
                KEYS.ENTREPRENEUR_TRADE_NAME.name to entity.tradeName,
                KEYS.ENTREPRENEUR_BIRTH_DATE.name to entity.birthDate.time.toString(),
                KEYS.ENTREPRENEUR_INDIVIDUAL.name to if (entity.individualEntrepreneur) "Sim" else "Não")

        val json = JSONObject(entrepreneurInfoMap)
        editor.putString(KEYS.ENTREPRENEUR_DATA.name, json.toString()).apply()
        return true
    }

    override fun delete(entity: Entrepreneur): Boolean {
        return true
    }

    override fun findAll(): List<Entrepreneur> = ArrayList()


}

data class EntrepreneurData(val id: Long,
                            val fullName: String,
                            val email: String,
                            val phone: Long,
                            val tradeName: String,
                            val birthDate: Date,
                            val individualEntrepreneur: Boolean)

enum class KEYS(val key: String) {
    SHARED_PREFERENCE_KEY("entrepreneur_db"),
    ENTREPRENEUR_FULL_NAME("full_name"),
    ENTREPRENEUR_EMAIL("email"),
    ENTREPRENEUR_PHONE("phone"),
    ENTREPRENEUR_TRADE_NAME("trade_name"),
    ENTREPRENEUR_BIRTH_DATE("birth_date"),
    ENTREPRENEUR_INDIVIDUAL("individual_entrepreneur"),
    ENTREPRENEUR_DATA("entrepreneur_data")
}