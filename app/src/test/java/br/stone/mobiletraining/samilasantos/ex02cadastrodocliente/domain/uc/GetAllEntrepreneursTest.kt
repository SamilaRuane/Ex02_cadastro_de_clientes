package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
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
        val repository = Mockito.mock(EntrepreneurRepository:: class.java)
        val objectReturned1 = Entrepreneur(1L,"Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)
        val objectReturned2 = Entrepreneur(1L,"Aka T", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)
        Mockito.`when`(repository.findAll()).thenReturn(arrayListOf(objectReturned1, objectReturned2))

        //Action
        val output = GetAllEntrepreneurs(repository).execute()

        //Check
        Assert.assertTrue(output.size == 2)
    }

    @Test
    fun `should return an empty list due repository not found exception` (){

        //Context
        val repository = Mockito.mock(EntrepreneurRepository:: class.java)
        Mockito.`when`(repository.findAll()).thenThrow(RepositoryNotFoundException ())

        //Action
        val output = GetAllEntrepreneurs(repository).execute()

        //Check
        Assert.assertTrue(output.isEmpty())
    }

    @Test
    fun `should return an empty list due unavailable repository exception` (){

        //Context
        val repository = Mockito.mock(EntrepreneurRepository:: class.java)
        Mockito.`when`(repository.findAll()).thenThrow(UnavailableRepositoryException ())

        //Action
        val output = GetAllEntrepreneurs(repository).execute()

        //Check
        Assert.assertTrue(output.isEmpty())
    }
}