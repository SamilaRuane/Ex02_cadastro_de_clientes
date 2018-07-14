package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class GetAllEntrepreneursTest {

    @Test
    fun `should get all users` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        val objectReturned1 = Entrepreneur ("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)
        val objectReturned2 = Entrepreneur ("Aka T", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)
        Mockito.`when`(repository.findAll()).thenReturn(arrayListOf(objectReturned1, objectReturned2))

        //Action
        val output = GetAllEntrepreneurs(repository).execute()

        //Check
        Assert.assertTrue(output.size == 2)
    }

    @Test
    fun `should return an empty list due repository not found exception` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        Mockito.`when`(repository.findAll()).thenThrow(RepositoryNotFoundException ())

        //Action
        val output = GetAllEntrepreneurs(repository).execute()

        //Check
        Assert.assertTrue(output.isEmpty())
    }

    @Test
    fun `should return an empty list due unavailable repository exception` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        Mockito.`when`(repository.findAll()).thenThrow(UnavailableRepositoryException ())

        //Action
        val output = GetAllEntrepreneurs(repository).execute()

        //Check
        Assert.assertTrue(output.isEmpty())
    }
}