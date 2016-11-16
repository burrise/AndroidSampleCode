
package edu.umkc.burrise.tddexample;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.mockito.Mockito;

// Note, in order for this test case to run
//   you need the following dependency in your
//   app's build.gradle file:
//       testCompile 'org.mockito:mockito-core:1.10.19'

@RunWith(MockitoJUnitRunner.class)
public class UnitTestWithMockObject {

    private static final String appName = "TDDExample";

    @Mock
    private Context mockContext;

    @Test
    public void appNameIsValid() {

        Mockito.when(mockContext.getString(R.string.app_name))
                .thenReturn(appName);
        AppInfo appInfo = new AppInfo(mockContext);

        String result = appInfo.getAppName();

        Assert.assertTrue("App name is invalid",
                result.equals(appName));
    }
}