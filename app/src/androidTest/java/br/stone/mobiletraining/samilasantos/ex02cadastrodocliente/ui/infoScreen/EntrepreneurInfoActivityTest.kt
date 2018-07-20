package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.domain.Entrepreneur
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.UiTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class EntrepreneurInfoActivityTest : UiTest() {

    @Rule
    @JvmField
    val mActivityRule = IntentsTestRule<EntrepreneurInfoActivity>(
            EntrepreneurInfoActivity::class.java, false, false)

    lateinit var intent: Intent

    @Before
    fun config() {
        intent = Intent()
        intent.putExtra("ENTREPRENEUR", Entrepreneur(
                fullName = "Empresário A",
                email = "empresarioonA@gmail.com",
                phone = 2122222222,
                tradeName = "Empresa A",
                birthDate = Date(),
                individualEntrepreneur = true))
    }

    @Test
    fun whenLaunchActivity_shouldDisplayAllFieldsFilled() {
        mActivityRule.launchActivity(intent)
        onView(withId(R.id.text_trade_name)).check(matches(isDisplayed()))
        onView(withId(R.id.text_owner_name)).check(matches(isDisplayed()))
        onView(withId(R.id.text_email)).check(matches(isDisplayed()))
        onView(withId(R.id.text_phone)).check(matches(isDisplayed()))
        onView(withId(R.id.text_birth_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_individual_entrepreneur)).perform(scrollTo()).check(matches(isDisplayed()))
    }
}