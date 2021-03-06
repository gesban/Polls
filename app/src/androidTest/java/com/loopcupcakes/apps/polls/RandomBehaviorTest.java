package com.loopcupcakes.apps.polls;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by evin on 2/8/16.®
 */
@RunWith(AndroidJUnit4.class)
public class RandomBehaviorTest {

    private static final String TAG = "RandomBehaviorTestTAG_";
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRandomBehavior() {
        clickOnRandomItem(R.id.a_main_recycler);
        clickOnRandomItem(R.id.a_slug_recycler);
        clickOnRandomItem(R.id.f_details_recycler);

        pressBack();
        clickOnRandomItem(R.id.f_details_recycler);

        pressBack();
        pressBack();
        clickOnRandomItem(R.id.a_slug_recycler);
        clickOnRandomItem(R.id.f_details_recycler);

        pressBack();
        pressBack();
        clickOnRandomItem(R.id.a_slug_recycler);
        clickOnRandomItem(R.id.f_details_recycler);

    }

    private void clickOnRandomItem(int viewId) {
        int x = getRandomRecyclerPosition(viewId);

        Log.d(TAG, "DoesMainListShowRandom: " + x);

        onView(withId(viewId))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(x, click()));
    }

    private int getRandomRecyclerPosition(int recyclerId) {
        Random ran = new Random();
        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(recyclerId);

        int n = (recyclerView == null)
                ? 1
                : recyclerView.getAdapter().getItemCount();
        return ran.nextInt(n);
    }

}
