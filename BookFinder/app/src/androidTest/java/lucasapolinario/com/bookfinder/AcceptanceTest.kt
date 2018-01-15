package lucasapolinario.com.bookfinder

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.test.ActivityInstrumentationTestCase2
import com.robotium.solo.Solo
import junit.framework.Assert
import lucasapolinario.com.bookfinder.views.activity.SplashActivity

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AcceptanceTest : ActivityInstrumentationTestCase2<SplashActivity>(SplashActivity::class.java) {


    protected lateinit var solo: Solo


    @Rule
    var activityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        solo = Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.activity)
    }

    @After
    @Throws(Exception::class)
    public override fun tearDown() {
        super.tearDown()
        solo.finishOpenedActivities()
    }

    @Test
    fun test1() {
        solo.typeText(0, "Lord of Ring")
        solo.waitForText("Loading ...")
        Assert.assertTrue(solo.waitForText("Lord of Ring"))
    }

}