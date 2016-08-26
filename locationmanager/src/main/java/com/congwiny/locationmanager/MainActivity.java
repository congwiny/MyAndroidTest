package com.congwiny.locationmanager;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mLocationTv;
    private TextView mCityTv;
    private TextView mStatusTv;
    private Button mReloadBtn;
    private Button mOpenGPSBtn;
    private LocationManager mLocationManager;
    private Subscription mSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initComponet();

    }


    private void initView() {
        mLocationTv = (TextView) findViewById(R.id.location);
        mCityTv = (TextView) findViewById(R.id.city);
        mStatusTv = (TextView) findViewById(R.id.status);
        mReloadBtn = (Button) findViewById(R.id.reload);
        mOpenGPSBtn = (Button) findViewById(R.id.openGPS);

        mReloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationTv.setText("waiting for reload..");
                mCityTv.setText("waiting for reload...");
                mStatusTv.setText("");

                if (!mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                } else {
                    reloadLocation();
                }
            }
        });

        mOpenGPSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
    }

    private void reloadLocation() {
        if (mSub != null && !mSub.isUnsubscribed()) {
            mSub.unsubscribe();
        }
        mSub = Observable.create(new Observable.OnSubscribe<List<Address>>() {
            @Override
            public void call(Subscriber<? super List<Address>> subscriber) {
                Location location = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                List<Address> locationList = getLocationList(location.getLatitude(), location.getLongitude());
                subscriber.onNext(locationList);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Address>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Address> locationList) {
                        if (!locationList.isEmpty()) {
                            Address address = locationList.get(0);
                            Log.i(TAG, "address =" + address);
                            String countryName = address.getCountryName();
                            Log.i(TAG, "countryName = " + countryName);
                            String countryCode = address.getCountryCode();
                            Log.i(TAG, "countryCode = " + countryCode);
                            String locality = address.getLocality();
                            Log.i(TAG, "locality = " + locality);

                            mLocationTv.setText("Longitude:" + address.getLongitude() + ",Latitude:" + address.getLatitude());
                            mCityTv.setText("Address:" + address);
                        } else {
                            mLocationTv.setText("no result");
                            mCityTv.setText("no result");
                        }
                    }
                });
    }

    private List<Address> getLocationList(double latitude, double longitude) {
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        List<Address> locationList = null;
        try {
            locationList = gc.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationList;
    }

    private void initComponet() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 2000, 3000, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.e(TAG, "on location changed");
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        mLocationTv.setText("Latitude：" + longitude + ", Longitude：" + latitude);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        mStatusTv.setText("onStatusChanged:" + s);
    }

    @Override
    public void onProviderEnabled(String s) {
        mStatusTv.setText("onProviderEnabled:" + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        mStatusTv.setText("onProviderDisabled:" + s);
    }
}
