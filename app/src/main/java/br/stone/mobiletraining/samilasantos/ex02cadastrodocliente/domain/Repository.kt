package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain

interface Repository {
    fun create (entity : Entrepreneur) : Boolean
    fun delete (entity : Entrepreneur) : Boolean
    fun findAll () : List<Entrepreneur>
}