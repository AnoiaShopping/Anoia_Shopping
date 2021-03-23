package dam.anoiashopping.gtidic.udl.cat;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import dam.anoiashopping.gtidic.udl.cat.views.register.RegisterActivity;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNull;

@RunWith (AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityScenarioRule <RegisterActivity> mActivityRule =
            new ActivityScenarioRule <> (RegisterActivity.class);


    @Test
    public void emailWithoutArrobaShowError() {
        String invalidEmail = "r.udl.cat";
        Espresso.onView(ViewMatchers.withId(R.id.i_Email)).perform(ViewActions.typeText(invalidEmail));
        closeSoftKeyboard();
        onView(withId(R.id.b_registrarse))
                .perform(click());
        onView(ViewMatchers.withId(R.id.i_Email))
                .check(ViewAssertions.matches(ViewMatchers.hasErrorText("Correu Invàlid")));
    }


}