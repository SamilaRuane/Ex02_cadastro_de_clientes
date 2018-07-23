package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data

import android.content.Context
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.common.Mapper
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

class SharedPreferencesRepository(val context: Context) : Repository {

    private val sharedPreferences = context.getSharedPreferences(KEYS.SHARED_PREFERENCE_KEY.key, Context.MODE_PRIVATE)

    override fun create(entity: Entrepreneur): Boolean {
        delete(entity)
        val editor = sharedPreferences.edit()
        val idNumber = (sharedPreferences.getLong(KEYS.LAST_ID_NUMBER.key, 0)) + 1
        val entrepreneurInfoMap = hashMapOf(
                KEYS.ENTREPRENEUR_ID.key to idNumber.toString(),
                KEYS.ENTREPRENEUR_FULL_NAME.key to entity.fullName,
                KEYS.ENTREPRENEUR_EMAIL.key to entity.email,
                KEYS.ENTREPRENEUR_PHONE.key to entity.phone.toString(),
                KEYS.ENTREPRENEUR_TRADE_NAME.key to entity.tradeName,
                KEYS.ENTREPRENEUR_BIRTH_DATE.key to entity.birthDate.timeInMillis.toString(),
                KEYS.ENTREPRENEUR_INDIVIDUAL.key to if (entity.individualEntrepreneur) "Sim" else "Não")

        val json = JSONObject(entrepreneurInfoMap)
        val existentData = sharedPreferences.getString(KEYS.ENTREPRENEUR_DATA.key, "")
        editor.putString(KEYS.ENTREPRENEUR_DATA.key,
                if (!existentData.isEmpty()) existentData + "," + json.toString()
                else json.toString()).apply()
        editor.putLong(KEYS.LAST_ID_NUMBER.key, idNumber).apply()
        return true
    }

    private fun create(entity: EntrepreneurData): Boolean {
        val editor = sharedPreferences.edit()
        val idNumber = (sharedPreferences.getLong(KEYS.LAST_ID_NUMBER.key, 0)) + 1
        val entrepreneurInfoMap = hashMapOf(
                KEYS.ENTREPRENEUR_ID.key to idNumber.toString(),
                KEYS.ENTREPRENEUR_FULL_NAME.key to entity.fullName,
                KEYS.ENTREPRENEUR_EMAIL.key to entity.email,
                KEYS.ENTREPRENEUR_PHONE.key to entity.phone.toString(),
                KEYS.ENTREPRENEUR_TRADE_NAME.key to entity.tradeName,
                KEYS.ENTREPRENEUR_BIRTH_DATE.key to entity.birthDate.toString(),
                KEYS.ENTREPRENEUR_INDIVIDUAL.key to (entity.individualEntrepreneur == "Sim"))

        val json = JSONObject(entrepreneurInfoMap)
        val existentData = sharedPreferences.getString(KEYS.ENTREPRENEUR_DATA.key, "")
        editor.putString(KEYS.ENTREPRENEUR_DATA.key,
                if (!existentData.isEmpty()) existentData + "," + json.toString()
                else json.toString()).apply()
        editor.putLong(KEYS.LAST_ID_NUMBER.key, idNumber).apply()
        return true
    }

    override fun delete(entity: Entrepreneur): Boolean = try {
        val data = (getData() as ArrayList)
        val targetObject = data.single { it.id == entity.id }
        data.remove(targetObject)

        sharedPreferences.edit().putString(KEYS.ENTREPRENEUR_DATA.key, "").apply()
        for (entrepreneurData in data) {
            create(entrepreneurData)
        }

        true
    } catch (e: NoSuchElementException) {
        false
    }

    override fun findAll(): List<Entrepreneur> {
        val entrepreneurList = getData()
        val mappedList = ArrayList<Entrepreneur>()
        for (data in entrepreneurList) {
            mappedList.add(Mapper.toEntrepreneur(data))
        }
        return mappedList
    }

    private fun getData(): List<EntrepreneurData> {
        val databaseData = "{ \"entrepreneurData\": [ " + sharedPreferences.getString(KEYS.ENTREPRENEUR_DATA.key, "") + "]}"
        return Gson().fromJson(databaseData, EntrepreneurList::class.java).entrepreneurData
    }
}

data class EntrepreneurList(val entrepreneurData: List<EntrepreneurData>)
data class EntrepreneurData(val id: Long,
                            @SerializedName("full_name")
                            val fullName: String,
                            val email: String,
                            val phone: Long,
                            @SerializedName("trade_name")
                            val tradeName: String,
                            @SerializedName("birth_date")
                            val birthDate: Long,
                            @SerializedName("individual_entrepreneur")
                            val individualEntrepreneur: String)

enum class KEYS(val key: String) {
    SHARED_PREFERENCE_KEY("entrepreneur_db"),
    ENTREPRENEUR_DATA("entrepreneur_data"),
    LAST_ID_NUMBER("last_id_number"),
    ENTREPRENEUR_ID("id"),
    ENTREPRENEUR_FULL_NAME("full_name"),
    ENTREPRENEUR_EMAIL("email"),
    ENTREPRENEUR_PHONE("phone"),
    ENTREPRENEUR_TRADE_NAME("trade_name"),
    ENTREPRENEUR_BIRTH_DATE("birth_date"),
    ENTREPRENEUR_INDIVIDUAL("individual_entrepreneur")
}