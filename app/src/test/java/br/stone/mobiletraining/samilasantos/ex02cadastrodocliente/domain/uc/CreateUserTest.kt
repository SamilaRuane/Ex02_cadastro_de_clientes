package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Interactor
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class CreateUserTest {

    @Test
    fun `should create a new user` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        val interactor = Mockito.mock(Interactor :: class.java)
        val objectUnderTest = Entrepreneur ("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)
        Mockito.`when`(repository.create(objectUnderTest)).thenReturn(true)

        //Action
        val output = CreateUser(repository, interactor).create(objectUnderTest)

        //Check
        Assert.assertTrue(output)
    }

    @Test
    fun `should delete a user` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        val interactor = Mockito.mock(Interactor :: class.java)
        val objectUnderTest = Entrepreneur ("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)
        Mockito.`when`(repository.delete(objectUnderTest)).thenReturn(true)

        //Action
        val output = CreateUser(repository, interactor).create(objectUnderTest)

        //Check
        Assert.assertTrue(output)
    }



}
