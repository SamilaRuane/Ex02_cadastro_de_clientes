package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.entrepreneurs.EntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class DeleteEntrepreneurTest {

    @Test
    fun `should delete a user` (){

        //Context
        val repository = Mockito.mock(EntrepreneurRepository:: class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)
        Mockito.`when`(repository.delete(objectUnderTest)).thenReturn(true)

        //Action
        val output = DeleteEntrepreneur(repository).execute(objectUnderTest)

        //Check
        assertTrue(output === Result.Success)
    }

    @Test
    fun `should return a repository not found exception` (){

        //Context
        val repository = Mockito.mock(EntrepreneurRepository:: class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)

        Mockito.`when`(repository.delete(objectUnderTest)).thenThrow(RepositoryNotFoundException ())

        //Action
        val output = DeleteEntrepreneur(repository).execute(objectUnderTest)

        //Check
        when (output) {
            is Result.Error -> assertTrue(output.code == ErrorCode.DELETING_ERROR)
        }
    }

    @Test
    fun `should return a unavailable repository exception` (){

        //Context
        val repository = Mockito.mock(EntrepreneurRepository:: class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)

        Mockito.`when`(repository.delete(objectUnderTest)).thenThrow(UnavailableRepositoryException ())

        //Action
        val output = DeleteEntrepreneur(repository).execute(objectUnderTest)

        //Check
        when (output) {
            is Result.Error -> assertTrue(output.code == ErrorCode.DELETING_ERROR)
        }
    }
}