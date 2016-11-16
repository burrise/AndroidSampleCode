package edu.umkc.burrise.tddexample;

import android.content.Context;

public class AppInfo {
    private Context context;

    public AppInfo(Context c) {
        this.context = c;
    }

    public String getAppName() {
        return context.getString(R.string.app_name);
    }
}
