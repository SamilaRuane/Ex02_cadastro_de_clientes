package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain

interface Repository {
    fun create (entity : Entrepreneur)
    fun delete (entity : Entrepreneur)
    fun findAll () : List<Entrepreneur>
}