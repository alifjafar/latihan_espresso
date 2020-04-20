package net.farnetwork.latihanespresso.kotlin

import android.app.Activity
import android.content.res.Resources
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import net.farnetwork.latihanespresso.R
import org.hamcrest.Matchers
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {
    private lateinit var wrongUsername: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var res: Resources
    private lateinit var activity: Activity

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Before
    fun initTest() {
        wrongUsername = "pengguna"
        username = "user"
        password = "password"
        activity = activityRule.activity
        res = activity.resources
    }

    /**
     * Test show errorText when login with empty username & password
     */
    @Test
    fun loginWithEmptyUsernamePassword() {
        onView(withId(R.id.textUsername)).perform(typeText(""))
        onView(withId(R.id.textPassword)).perform(typeText(""))
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withId(R.id.textUsername)).check(matches(
                hasErrorText(res.getString(R.string.emptyUsername))))
        onView(withId(R.id.textPassword)).check(matches(
                hasErrorText(res.getString(R.string.emptyPassword))))
    }

    /**
     * Test show toast wrong password string when login with wrong username
     */
    @Test
    fun loginWithWrongUsername() {
        onView(withId(R.id.textUsername)).perform(typeText(wrongUsername))
        onView(withId(R.id.textPassword)).perform(typeText(password))
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText(R.string.wrongUsernamePassword))
                .inRoot(withDecorView(Matchers.not(`is`(activity.window.decorView))))
                .check(matches(isDisplayed()))
    }

    /**
     * Test show toast login success string when login with valid username & password
     */
    @Test
    fun loginWithValidUsernamePassword() {
        onView(withId(R.id.textUsername)).perform(typeText(username))
        onView(withId(R.id.textPassword)).perform(typeText(password))
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withText(res.getString(R.string.welcomeMessage, username)))
                .inRoot(withDecorView(Matchers.not(`is`(activity.window.decorView))))
                .check(matches(isDisplayed()))
    }
}