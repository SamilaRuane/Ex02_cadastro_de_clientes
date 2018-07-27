package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.data.entrepreneurs.TestEntrepreneurRepository
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.UiTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntrepreneurInfoActivityTest {

    @Rule
    @JvmField
    val mActivityRule = IntentsTestRule<EntrepreneurInfoActivity>(
            EntrepreneurInfoActivity::class.java, false, false)

    lateinit var intent: Intent

    @Before
    fun config() {
        intent = Intent()
        intent.putExtra("ENTREPRENEUR", 0)
    }

    @Test
    fun whenLaunchActivity_shouldDisplayAllFieldsFilled() {
        UiTest.defineTestRepository(TestEntrepreneurRepository())

        mActivityRule.launchActivity(intent)
        onView(withId(R.id.text_trade_name)).check(matches(isDisplayed()))
        onView(withId(R.id.text_owner_name)).check(matches(isDisplayed()))
        onView(withId(R.id.text_email)).check(matches(isDisplayed()))
        onView(withId(R.id.text_phone)).check(matches(isDisplayed()))
        onView(withId(R.id.text_birth_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_individual_entrepreneur)).perform(scrollTo()).check(matches(isDisplayed()))
    }

    @Test
    fun whenClickDeleteButton_shouldDisplayAnSuccessDialog() {
        UiTest.defineTestRepository(TestEntrepreneurRepository())

        mActivityRule.launchActivity(intent)
        onView(withId(R.id.button_delete)).perform(click())
        onView(ViewMatchers.withText("Operação realizada com sucesso")).check(matches(isDisplayed()))
        onView(withId(android.R.id.button1)).perform(click())
    }
}