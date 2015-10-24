package com.example.dante.joinme;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.microsoft.windowsazure.mobileservices.*;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import com.database.test.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.table.query.ExecutableQuery;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MobileServiceClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        test();
    }

    private void test() {
        new Thread() {
            @Override
            public void run() {
                try {
                    mClient = new MobileServiceClient(
                            "https://join-me.azure-mobile.net/",
                            "cNsrvVDiQFZLIvfJXQbChGrKlZrckT34",
                            MapsActivity.this
                    );
                    Test2 mTest = new Test2();
                    mTest.tttest = "他媽的就說測試啦";
                    MobileServiceTable mTable = mClient.getTable(Test2.class);
                    MobileServiceList<Test2> results = null;
                    try {
                        Log.e("lala", "AAA");
                        results = (MobileServiceList<Test2>) mTable.execute().get();
                        for (Test2 item : results) {
                            Log.e("lala", item.tttest);
                        }
                        Log.e("lala", "BBB");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (MobileServiceException e) {
                        e.printStackTrace();
                    }
////            mTable.update();
//
//            mClient.getTable(Test2.class).insert(mTest, new TableOperationCallback<Test2>() {
//                public void onCompleted(Test2 entity, Exception exception, ServiceFilterResponse response) {
//                    if (exception == null) {
//                        // Insert succeeded
//                    } else {
//                        // Insert failed
//                        exception.printStackTrace();
//                    }
//                }
//            });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        Location e;
//        new LatLng(e.getLatitude(), e.getLongitude())
        LatLng sydney = new LatLng(-34, 151);
        Marker myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("19:00~21:00"));
        myMarker.setSnippet("星巴克買一送一");
        myMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.red_marker));
        myMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12), 1000, null);
    }
}
