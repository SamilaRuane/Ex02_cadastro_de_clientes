package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode

sealed class Result {
    object Success : Result()
    data class Error( val code: ErrorCode ) : Result()
}