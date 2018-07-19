package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.GraphConfigurator
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EntrepreneurListActivityTest {

    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule<EntrepreneurListActivity>(
            EntrepreneurListActivity::class.java, false, false)


    @Test
    fun whenActivityIsLaunched_shouldDisplayAList() {
        mActivityRule.launchActivity(null)
        Espresso.onView(withId(R.id.recycler_main_entrepreneurs)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.button_add)).check(matches(isDisplayed()))
    }

    companion object {
        @BeforeClass
        @JvmStatic
        fun config() {
            GraphConfigurator.getInstance(whoCalls = this)
        }
    }
}