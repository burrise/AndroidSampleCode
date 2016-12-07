package edu.umkc.burrise.automatedguitest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.v7.app.AppCompatActivity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleGUITest {

    // The following is needed to launch activity under test.
    // You don't need to reference mActivityRule
    //   below. onView() will find UI components in active
    //   view. The following example shows how onView()
    //   works as the active view changes
    // https://github.com/googlesamples/android-testing/blob/master/ui/espresso/BasicSample/app/src/androidTest/java/com/example/android/testing/espresso/BasicSample/ChangeTextBehaviorTest.java
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void foo() {
        onView(withId(R.id.inputText)).perform(typeText("Amazing"), closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textLabel)).check(matches(withText("Amazing")));

        // Or, you can perform all three (find component,
        //   perform interaction, check result) with one statement:
      /*
        onView(withId(R.id.my_view))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(matches(withText("Amazing"))); // matches(isDisplayed()) is a ViewAssertion
      */
    }
}