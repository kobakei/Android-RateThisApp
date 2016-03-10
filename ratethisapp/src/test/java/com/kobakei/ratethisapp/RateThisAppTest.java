package com.kobakei.ratethisapp;

import android.content.Context;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowSystemClock;

/**
 * Created by keisuke on 16/03/09.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSystemClock.class})
public class RateThisAppTest {

    @Test
    public void shouldRateDialogIfNeeded_LaunchTimeIsCorrect() {
        Context context = RuntimeEnvironment.application.getApplicationContext();

        RateThisApp.init(new RateThisApp.Config(1, 3));

        RateThisApp.onStart(context);
        Assert.assertFalse(RateThisApp.shouldShowRateDialog());
        RateThisApp.onStart(context);
        Assert.assertFalse(RateThisApp.shouldShowRateDialog());
        RateThisApp.onStart(context);
        Assert.assertTrue(RateThisApp.shouldShowRateDialog());
        RateThisApp.onStart(context);
        Assert.assertTrue(RateThisApp.shouldShowRateDialog());
    }
}
