package com.example.dante.joinme;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

import com.database.test.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

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

        try {
            mClient = new MobileServiceClient(
                    "https://join-me.azure-mobile.net/",
                    "cNsrvVDiQFZLIvfJXQbChGrKlZrckT34",
                    this
            );
            Test2 mTest = new Test2();
            mTest.tttest = "他媽的就說測試啦2";
            MobileServiceTable mTable = mClient.getTable(Test2.class);
//            mTable.update();

            mClient.getTable(Test2.class).insert(mTest, new TableOperationCallback<Test2>() {
                public void onCompleted(Test2 entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        // Insert succeeded
                    } else {
                        // Insert failed
                        exception.printStackTrace();
                    }
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        Marker myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("19:00~21:00"));
        myMarker.setSnippet("星巴克買一送一");
        myMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.red_marker));
        myMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
