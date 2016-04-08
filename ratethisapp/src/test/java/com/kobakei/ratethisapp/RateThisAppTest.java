package com.kobakei.ratethisapp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.res.builder.RobolectricPackageManager;
import org.robolectric.shadows.ShadowSystemClock;

/**
 * Created by keisuke on 16/03/09.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSystemClock.class})
public class RateThisAppTest {

    @Before
    public void setUp() throws PackageManager.NameNotFoundException {
        Context context = RuntimeEnvironment.application.getApplicationContext();

        // Assume app just installed
        RobolectricPackageManager roboPackMan = RuntimeEnvironment.getRobolectricPackageManager();
        PackageInfo pkgInfo = roboPackMan.getPackageInfo(context.getPackageName(), 0);
        pkgInfo.firstInstallTime = System.currentTimeMillis();
    }

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
