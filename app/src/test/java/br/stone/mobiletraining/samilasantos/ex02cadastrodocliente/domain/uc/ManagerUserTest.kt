package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import org.mockito.Mockito
import java.util.*

class ManagerUserTest {
    fun `should create a new user` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        val objectUnderTest = Entrepreneur ("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)
        //Action

        //Check
    }
}
