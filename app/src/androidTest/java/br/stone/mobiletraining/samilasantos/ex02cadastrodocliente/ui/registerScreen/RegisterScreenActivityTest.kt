package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterScreenActivityTest {
    @Rule
    @JvmField
    val mActivityRule = IntentsTestRule<RegisterActivity>(
            RegisterActivity::class.java, false, true)

    @Test
    fun confirmButton_shouldBeDisabled() {
        onView(withId(R.id.button_confirm)).check(matches(not(isEnabled())))
    }

    @Test
    fun confirmButton_shouldBeEnabled (){
        fillFieldsWithCorrectInfo()
        onView(withId(R.id.button_confirm)).check(matches(isEnabled()))
    }

    @Test
    fun whenClickOnConfirmButton_shouldDisplayAnIncorrectInfoErrorDialog() {
        fillFieldsWithIncorrectInfo()
        onView(withId(R.id.button_confirm)).perform(click())
        onView(withText("Por favor preencha todos os campos com informações válidas.")).check(matches(isDisplayed()))
    }

    @Test
    fun whenClickOnConfirmButton_shouldDisplayAnSuccessDialog() {
        fillFieldsWithCorrectInfo()
        onView(withId(R.id.button_confirm)).perform(click())
        onView(withText("Operação realizada com sucesso")).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).perform(click())
    }

    @Test
    fun whenClickOnOkDialogButton_shouldFinishActivity() {
        fillFieldsWithCorrectInfo()
        onView(withId(R.id.button_confirm)).perform(click())
        onView(withText("Operação realizada com sucesso")).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).perform(click())
        Assert.assertTrue(mActivityRule.activity.isFinishing)
    }

    private fun fillFieldsWithIncorrectInfo() {
        onView(withId(R.id.edit_full_name)).perform(typeText("Empresario 1"))
        onView(withId(R.id.edit_email)).perform(typeText("empresarioone@gmail.com"))
        onView(withId(R.id.edit_phone)).perform(typeText("21222"))
        onView(withId(R.id.edit_trade_name)).perform(typeText("Empresa 1")).perform(closeSoftKeyboard())
        onView(withId(R.id.edit_birth_date)).perform(click()).perform(click())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.edit_birth_date)).perform(closeSoftKeyboard())
        onView(withId(R.id.radio_button_yes)).perform(click())
        onView(withId(R.id.edit_full_name)).perform(click()).perform(closeSoftKeyboard())
    }

    private fun fillFieldsWithCorrectInfo() {
        onView(withId(R.id.edit_full_name)).perform(typeText("Empresario 1"))
        onView(withId(R.id.edit_email)).perform(typeText("empresarioone@gmail.com"))
        onView(withId(R.id.edit_phone)).perform(typeText("2122222222"))
        onView(withId(R.id.edit_trade_name)).perform(typeText("Empresa 1")).perform(closeSoftKeyboard())
        onView(withId(R.id.edit_birth_date)).perform(click()).perform(click())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.edit_birth_date)).perform(closeSoftKeyboard())
        onView(withId(R.id.radio_button_yes)).perform(click())
        onView(withId(R.id.edit_full_name)).perform(click()).perform(closeSoftKeyboard())
    }
}