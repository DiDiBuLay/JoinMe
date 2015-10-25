package com.example.dante.joinme;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.JoinMe.event;
import com.JoinMe.member;
import com.cloud.JMCloud;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.microsoft.windowsazure.mobileservices.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MobileServiceClient mClient;

    private Button mConfirmBtn;

    private Button[] mBtns = new Button[4];
    private FrameLayout mContent;

    private DefaultFragment mDefaultFragment;
    private EditFragment mEditFragment;
    private CreateFragment mCreateFragment;

    private event mShowEvent;

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
        setEventListOnMap();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.e("lala","MarkerClick");
                mContent.setVisibility(View.VISIBLE);
//                JMCloud.getInstance().getEvent(marker.getPosition().latitude, marker.getPosition().longitude, new JMCloud.OnCloudResultListener() {
//                    @Override
//                    public void onDone(MobileServiceList list, member member, event event) {
//                        mShowEvent = event;
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mDefaultFragment.updateUi(mShowEvent);
//                                changePage(0);
//                            }
//                        });
//                    }
//                });
                return true;
            }
        });
//        Location e;
//        new LatLng(e.getLatitude(), e.getLongitude())
    }

    private void initialComponent() {
        mContent = (FrameLayout) findViewById(R.id.detail_content);

        mConfirmBtn = (Button) findViewById(R.id.btn_confirm);
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JMCloud cloud = JMCloud.getInstance();
                cloud.createAnEvent(CreateFragment.getNewEvent());
            }
        });

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

        mDefaultFragment = DefaultFragment.newInstance(mShowEvent);
        mEditFragment = EditFragment.newInstance(mShowEvent);
        mCreateFragment = CreateFragment.newInstance();
    }

    private void updateUi(final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("lala", "Index:" + index);
                updateBtnColor(index);
            }
        });
        changePage(index);
    }

    private void updateBtnColor(int index) {
        for (int i = 0; i < mBtns.length; i++) {
            mBtns[i].setBackgroundColor(getResources().getColor(R.color.white));
        }
        mBtns[index].setBackgroundColor(getResources().getColor(R.color.green_btn));
    }

    private void changePage(final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConfirmBtn.setVisibility(View.GONE);
                mContent.setVisibility(View.VISIBLE);
                switch (index) {
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.detail_content, mDefaultFragment)
                                .commitAllowingStateLoss();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.detail_content, mEditFragment)
                                .commitAllowingStateLoss();
                        break;
                    case 2:
                        break;
                    case 3:
                        getFragmentManager().beginTransaction().replace(R.id.detail_content, mCreateFragment)
                                .commitAllowingStateLoss();
                        mConfirmBtn.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    private void setEventListOnMap() {
        new Thread() {
            @Override
            public void run() {
                JMCloud cloud = JMCloud.createInstance(MapsActivity.this);
                cloud.getEventList(new JMCloud.OnCloudResultListener() {
                    @Override
                    public void onDone(MobileServiceList list, member member, event event) {
                        final MobileServiceList<event> eventList = (MobileServiceList<event>) list;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (event mEvent : eventList) {
                                    LatLng sydney = new LatLng(mEvent.getLatitude(), mEvent.getLongitude());
                                    Marker myMarker = mMap.addMarker(new MarkerOptions().position(sydney).title(mEvent.getTitle()));
                                    myMarker.setSnippet(mEvent.getContent());
                                    myMarker.showInfoWindow();
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(12), 1000, null);
                                }
                            }
                        });
                    }
                });
            }
        }.start();
    }
}
