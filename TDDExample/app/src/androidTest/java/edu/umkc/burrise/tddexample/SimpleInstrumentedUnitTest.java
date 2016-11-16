package edu.umkc.burrise.tddexample;

//import android.support.test.filters.SmallTest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/*
 * make sure to specify AndroidJUnitRunner as the default test
 *  instrumentation runner in your project by including the following
 *   setting in your app's module-level build.gradle file:
 *   android {
 *     defaultConfig {
 *       testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
 *     }
 *   }
 *   And,
 *     androidTestCompile 'com.android.support:support-annotations:23.4.0'
 *     androidTestCompile 'com.android.support.test:runner:0.5'
 *   under dependencies.
 *   See build.gradle for module for details
 */

// have to specify the class that will act as the runner of the test,
//   instead of the default runner: JUnit.
@RunWith(AndroidJUnit4.class)
// Generally, we can assume small tests almost as unit tests, medium
//   tests as integration tests, and large tests as end-to-end tests.
@SmallTest
public class SimpleInstrumentedUnitTest {

    private static final String appName = "TDDExample";

    private Context appContext;
    private AppInfo appInfo;

    @Before
    public void setup() {
        appContext = InstrumentationRegistry.getTargetContext();
        appInfo = new AppInfo(appContext);
    }

    @Test
    public void appNameIsValid() {

        String result = appInfo.getAppName();

        Assert.assertTrue("App name is invalid",
                result.equals(appName));
    }

}
