package edu.umkc.burrise.gpsexample;
// https://developer.android.com/guide/topics/location/strategies.html#Flow
// https://web.archive.org/web/20100429083435/http://marakana.com/forums/android/android_examples/42.html
/*
   Location Services
   -----------------
   There are two strategies for getting location.
   The method described here uses the platform location API in
   android.location. The Google Location Services API, part of Google
   Play Services, provides a more powerful, high-level framework that
   automatically handles location providers, user movement, and
   location accuracy.

   Permissions
   -----------

   Every Android app runs in a limited-access sandbox. If an app needs to
   use resources or information outside of its own sandbox, the app has to
   request the appropriate permission. If an app needs to use resources or
   information outside of its own sandbox, the app has to request the
   appropriate permission. You declare that your app needs a permission by
   listing the permission in the App Manifest.

   Depending on the platform version, the user grants the permission either
   when they install the app (on Android 5.1 and lower) or while running
   the app (on Android 6.0 and higher). (Android 6.0 is API level 23)

   If the device is running Android 6.0 or higher, and your app's target
   SDK is 23 or higher: The app has to list the permissions in the manifest,
   and it must request each dangerous permission it needs while the app is
   running. The user can grant or deny each permission, and the app can
   continue to run with limited capabilities even if the user denies a
   permission request.

   This example demonstrates how to request permissions at runtime.



 */
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {
    static final private String TAG = "GPS";
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 123;
    private static final int MY_PERMISSIONS_REGISTER_FOR_UPDATES = MY_PERMISSIONS_REQUEST_FINE_LOCATION + 1;

    private LocationManager locationManager;
    private String bestProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View locationButton = findViewById(R.id.button);
        // This class implements the onClickListener interface.
        // Passing 'this' to setOnClickListener means the
        //   onClick method in this class will get called
        //   when the button is clicked.
        locationButton.setOnClickListener(this);

        // Get the location manager
        // LOCATION_SERVICE is a constant in class Context (a super class of Activity)
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Not required.
        // List all providers.
        List<String> providers = locationManager.getAllProviders();
        for (String provider : providers) {
            Log.i(TAG, "Provider: " + provider);
        }

        Criteria criteria = new Criteria();
        bestProvider = locationManager.getBestProvider(criteria, false);

        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REGISTER_FOR_UPDATES);
        }
        else {
            // Register for updates
            registerForUpdates();
        }

    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "Starting onClick...");

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        }
        else {
            getCurrentLocation();
        }
    }

    //@TargetApi(23)
    private void getCurrentLocation() {
        try {
            // Example of how to query GPS location.
            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
// Returns null if location isn't available
// In emulator, you need to send your location (via android / tools)
//   before getLostKnownLocation() will return a location.
            if (location != null) {
                displayLocation(location);
            }
            else {
                Log.i(TAG, "location was null");
            }
        }
        catch(SecurityException e) {
            Log.i(TAG, "Problem getting location: " + e);
        }
    }
    //@TargetApi(23)
    private void displayLocation(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        Toast.makeText(getApplicationContext(),
                "Lat " + latitude + " Long " + longitude, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.i(TAG, "Starting onRequestPermissionResult");
        switch (requestCode) {
            case MY_PERMISSIONS_REGISTER_FOR_UPDATES:
                // Register for updates
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registerForUpdates();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Can't register for updates at this time.", Toast.LENGTH_LONG).show();
                }
                break;

            case MY_PERMISSIONS_REQUEST_FINE_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getCurrentLocation();

                } else {
                    // permission denied! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(),
                            "Can't get location. Permission denied", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void registerForUpdates() {
        try {
            // 20000 = minimum time interval between location updates, in milliseconds
            // 1 = minimum distance between location updates, in meters
            locationManager.requestLocationUpdates(bestProvider, 20000, 1, this);
            // OR
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1, this);
        } catch (SecurityException e) {
            Log.i(TAG, "Problem registering for updates: " + e);
        }
    }

    // *** Begin Location callback methods ***
    @Override
    public void onLocationChanged(Location location) {
        displayLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    // *** End Location callback methods ***
}

