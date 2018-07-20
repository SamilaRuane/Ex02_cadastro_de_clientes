package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.screenList

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.R
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di.*
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.infoScreen.EntrepreneurInfoActivity
import br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.registerScreen.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class EntrepreneurListActivityTest {

    @Rule
    @JvmField
    val mActivityRule = IntentsTestRule<EntrepreneurListActivity>(
            EntrepreneurListActivity::class.java, false, false)


    @Test
    fun whenActivityIsLaunched_shouldDisplayAList() {
        defineTestRepository()
        mActivityRule.launchActivity(null)
        Espresso.onView(withId(R.id.recycler_main_entrepreneurs)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.button_add)).check(matches(isDisplayed()))
    }

    @Test
    fun whenActivityIsLaunched_shouldDisplayNoClientText() {
        GraphConfigurator.getInstance().mode = Mode.Test(GraphBuilder.builder()
                .override()
                .repository(EmptyRepository())
                .build()!!)
        mActivityRule.launchActivity(null)
        Espresso.onView(withId(R.id.text_empty_view)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.button_add)).check(matches(isDisplayed()))
    }

    @Test
    fun whenClickOnItem_shouldOpenInfoActivity() {
        defineTestRepository()
        mActivityRule.launchActivity(null)

        onView(withId(R.id.recycler_main_entrepreneurs)).perform(RecyclerViewActions
                .actionOnItemAtPosition<EntrepreneurAdapter.ItemViewHolder>(1, click()))
        intended(hasComponent(EntrepreneurInfoActivity::class.java.name))
    }

    @Test
    fun whenClickOnButton_shouldOpenRegisterActivity() {
        defineTestRepository()
        mActivityRule.launchActivity(null)
        onView(withId(R.id.button_add)).perform(click())
        intended(hasComponent(RegisterActivity::class.java.name))
    }

    private fun defineTestRepository() {
        GraphConfigurator.getInstance().mode = Mode.Test(GraphBuilder.builder()
                .override()
                .repository(TestRepository())
                .build()!!)
    }
}