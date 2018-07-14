package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class DeleteEntrepreneurTest {

    @Test
    fun `should delete a user` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        val objectUnderTest = Entrepreneur ("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)
        Mockito.`when`(repository.delete(objectUnderTest)).thenReturn(true)

        //Action
        val output = DeleteEntrepreneur(repository).execute(objectUnderTest)

        //Check
        Assert.assertTrue(output.status == Result.SUCCESS)
    }

    @Test
    fun `should return a repository not found exception` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        val objectUnderTest = Entrepreneur ("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)

        Mockito.`when`(repository.delete(objectUnderTest)).thenThrow(RepositoryNotFoundException ())

        //Action
        val output = DeleteEntrepreneur(repository).execute(objectUnderTest)

        //Check
        Assert.assertTrue(output.status == Result.ERROR)
    }

    @Test
    fun `should return a unavailable repository exception` (){

        //Context
        val repository = Mockito.mock(Repository :: class.java)
        val objectUnderTest = Entrepreneur ("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Date(), true)

        Mockito.`when`(repository.delete(objectUnderTest)).thenThrow(UnavailableRepositoryException ())

        //Action
        val output = DeleteEntrepreneur(repository).execute(objectUnderTest)

        //Check
        Assert.assertTrue(output.status == Result.ERROR)
    }
}