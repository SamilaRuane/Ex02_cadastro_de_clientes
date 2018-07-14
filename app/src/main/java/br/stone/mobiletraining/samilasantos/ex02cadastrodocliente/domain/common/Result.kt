package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common

data class Result (val status : Int, val message : String){
    companion object {
        const val SUCCESS = 93829
        const val ERROR = 763473
    }
}