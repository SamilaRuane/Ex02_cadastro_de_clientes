package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Repository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.common.Result
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.RepositoryNotFoundException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.exceptions.UnavailableRepositoryException
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.common.ErrorCode
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class CreateEntrepreneurTest {

    @Test
    fun `should create a new user`() {

        //Context
        val repository = Mockito.mock(Repository::class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)
        Mockito.`when`(repository.create(objectUnderTest)).thenReturn(true)

        //Action
        val output = CreateEntrepreneur(repository).execute(objectUnderTest)

        //Check
        Assert.assertTrue(output === Result.Success)
    }

    @Test
    fun `should return a repository not found exception`() {

        //Context
        val repository = Mockito.mock(Repository::class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)

        Mockito.`when`(repository.create(objectUnderTest)).thenThrow(RepositoryNotFoundException())

        //Action
        val output = CreateEntrepreneur(repository).execute(objectUnderTest)

        //Check
        when (output) {
            is Result.Error -> Assert.assertTrue(output.code == ErrorCode.SAVING_ERROR)
        }
    }

    @Test
    fun `should return a unavailable repository exception`() {

        //Context
        val repository = Mockito.mock(Repository::class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "minhaempresa@gmail.com", 2122222222, "Foo Company", Calendar.getInstance(), true)

        Mockito.`when`(repository.create(objectUnderTest)).thenThrow(UnavailableRepositoryException())

        //Action
        val output = CreateEntrepreneur(repository).execute(objectUnderTest)

        //Check
        when (output) {
            is Result.Error -> Assert.assertTrue(output.code == ErrorCode.SAVING_ERROR)
        }
    }


    @Test
    fun `should return an Error due non filled email field`() {

        //Context
        val repository = Mockito.mock(Repository::class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "", 2122222222, "Foo Company", Calendar.getInstance(), true)

        Mockito.`when`(repository.create(objectUnderTest)).thenReturn(true)

        //Action
        val output = CreateEntrepreneur(repository).execute(objectUnderTest)

        //Check
        when (output) {
            is Result.Error -> Assert.assertTrue(output.code == ErrorCode.INCORRECT_INFO)
        }
    }

    @Test
    fun `should return an Error due incorrect phone`() {

        //Context
        val repository = Mockito.mock(Repository::class.java)
        val objectUnderTest = Entrepreneur("Foo Bob", "minhaempresa@gmail.com", 219999, "Foo Company", Calendar.getInstance(), true)

        Mockito.`when`(repository.create(objectUnderTest)).thenReturn(true)

        //Action
        val output = CreateEntrepreneur(repository).execute(objectUnderTest)

        //Check
        when (output) {
            is Result.Error -> Assert.assertTrue(output.code == ErrorCode.INCORRECT_INFO)
        }
    }
}
