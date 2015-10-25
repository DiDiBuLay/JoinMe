package com.example.dante.joinme;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.JoinMe.JMEvent;
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

    private Button[] mBtns = new Button[4];
    private FrameLayout mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initialComponent();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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

    private void initialComponent() {
        mContent = (FrameLayout)findViewById(R.id.detail_content);
        mBtns[0] = (Button) findViewById(R.id.btn_near);
        mBtns[1] = (Button) findViewById(R.id.btn_joined);
        mBtns[2] = (Button) findViewById(R.id.btn_ijo);
        mBtns[3] = (Button) findViewById(R.id.btn_new_ijo);
        //Just set btn click listener.
        for (int i = 0; i < mBtns.length; i++) {
            final int index = i;
            mBtns[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateUi(index);
                }
            });
        }
        updateUi(0);
    }

    private void updateUi(int index) {
        updateBtnColor(index);
        changePage(index);
    }

    private void updateBtnColor(int index) {
        for (int i = 0; i < mBtns.length; i++) {
            mBtns[i].setBackgroundColor(getResources().getColor(R.color.white));
        }
        mBtns[index].setBackgroundColor(getResources().getColor(R.color.green_btn));
    }

    private void changePage(int index) {
        switch (index) {
            case 0:
                JMEvent event = new JMEvent();
                getFragmentManager().beginTransaction().replace(R.id.detail_content, DefaultFragment.newInstance(event))
                        .commitAllowingStateLoss();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
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
}
