package net.farnetwork.latihanespresso.java;

import android.app.Activity;
import android.content.res.Resources;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import net.farnetwork.latihanespresso.R;
import net.farnetwork.latihanespresso.java.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private String wrongUsername;
    private String username;
    private String password;
    private Resources res;
    private Activity activity;

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void initTest() {
        wrongUsername = "pengguna";
        username = "user";
        password = "password";
        activity = activityRule.getActivity();
        res = activity.getResources();
    }


    /**
     *  Test show errorText when login with empty username & password
     */
    @Test
    public void loginWithEmptyUsernamePassword() {
        onView(ViewMatchers.withId(R.id.textUsername)).perform(typeText(""));
        onView(withId(R.id.textPassword)).perform(typeText(""));

        onView(withId(R.id.btnLogin)).perform(click());

        onView(withId(R.id.textUsername)).check(matches(
                hasErrorText(res.getString(R.string.emptyUsername))));
        onView(withId(R.id.textPassword)).check(matches(
                hasErrorText(res.getString(R.string.emptyPassword))));
    }

    /**
     *  Test show toast wrong password string when login with wrong username
     */
    @Test
    public void loginWithWrongUsername() {
        onView(withId(R.id.textUsername)).perform(typeText(wrongUsername));
        onView(withId(R.id.textPassword)).perform(typeText(password));

        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText(R.string.wrongUsernamePassword))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

    /**
     *  Test show toast login success string when login with valid username & password
     */
    @Test
    public void loginWithValidUsernamePassword() {
        onView(withId(R.id.textUsername)).perform(typeText(username));
        onView(withId(R.id.textPassword)).perform(typeText(password));

        onView(withId(R.id.btnLogin)).perform(click());

        onView(withText(res.getString(R.string.welcomeMessage, username)))
                .inRoot(withDecorView(not(is(activity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }
}