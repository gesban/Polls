package com.loopcupcakes.apps.polls;

/**
 * Created by evin on 2/5/16.
 */


import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class NormalBehaviorTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void doesMainListShow() {
        onView(withId(R.id.a_main_recycler))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(0, click()));
    }

}
