package dam.anoiashopping.gtidic.udl.cat;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import dam.anoiashopping.gtidic.udl.cat.views.register.RegisterActivity;

import static org.junit.Assert.assertNull;

@RunWith (AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityTestRule <RegisterActivity> mActivityRule =
            new ActivityTestRule <> (RegisterActivity.class);

    private RegisterActivity mActivity;

    @Before
    public void setUp () throws IOException {
        mActivity = mActivityRule.getActivity();
    }

    @Test
    public void testEmailError () {
        Espresso.onView(ViewMatchers.withId(R.id.b_Registre)).perform(ViewActions.click());


    }
}