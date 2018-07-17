package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common

import android.content.Context
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R

class ErrorMapper(val context: Context) {
    fun getFeedbackMessage(code: ErrorCode) : String {
        return when (code){
             ErrorCode.INCORRECT_INFO -> context.getString(R.string.no_valid_info_feedback)
             ErrorCode.UNKNOWN_ERROR -> context.getString(R.string.error_feedback)
             ErrorCode.INVALID_DATE -> context.getString(R.string.invalid_date_feedback)
             ErrorCode.DELETING_ERROR -> context.getString(R.string.deleting_user_error_feedback)
             ErrorCode.SAVING_ERROR -> context.getString(R.string.saving_user_error_feedback)
         }
    }
}

enum class ErrorCode (val code : Int) {
    INCORRECT_INFO (454545),
    UNKNOWN_ERROR (48784),
    INVALID_DATE (1478532),
    DELETING_ERROR(852963),
    SAVING_ERROR (654665)
}