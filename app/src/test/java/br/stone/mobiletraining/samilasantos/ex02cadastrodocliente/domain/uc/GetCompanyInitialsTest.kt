package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.uc

import org.junit.Assert
import org.junit.Test

class GetCompanyInitialsTest {

    @Test
    fun `should return sentence initials`() {

        // Context
        val sentence = "Samila Ruane"
        // Action
        val output = GetCompanyInitials.process(sentence)
        // Check
        Assert.assertEquals( "SR",  output)
    }

    @Test
    fun `should return at least 3 words from a sentence`() {

        // Context
        val sentence = "Samila Ruane B Santos"
        // Action
        val output = GetCompanyInitials.process(sentence).length
        // Check
        Assert.assertTrue(  output <= 3 )
    }

    @Test
    fun `should return an empty string`() {

        // Context
        val sentence = ""
        // Action
        val output = GetCompanyInitials.process(sentence)
        // Check
        Assert.assertTrue( output.isEmpty() )
    }
}