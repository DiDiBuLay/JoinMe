package com.cloud;

import android.content.Context;
import android.util.Log;

import com.JoinMe.event;
import com.JoinMe.member;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

/**
 * Created by dante on 2015/10/25.
 */
public class JMCloud {
    public final static int USER_ID = 0;
    private static JMCloud sCloud;
    private static Context sContext;

    private static MobileServiceClient sClient;

    public interface OnCloudResultListener {
        void onDone(MobileServiceList list, member member, event event);
    }

    public static JMCloud createInstance(Context context) {
        sContext = context;
        sCloud = new JMCloud();
        return sCloud;
    }

    public static JMCloud getInstance() {
        Log.e("lala","getInstance");
        return sCloud == null ? new JMCloud() : sCloud;
    }

    private JMCloud() {
        try {
            sClient = new MobileServiceClient(
                    "https://join-me.azure-mobile.net/",
                    "cNsrvVDiQFZLIvfJXQbChGrKlZrckT34", sContext
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getUserList(OnCloudResultListener listener) {
        MobileServiceTable table = sClient.getTable(member.class);
        try {
            listener.onDone((MobileServiceList<member>) table.execute().get(), null, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
    }

    public void getUser(int id, OnCloudResultListener listener) {
        MobileServiceTable table = sClient.getTable(member.class);
        try {
            MobileServiceList<member> userList = (MobileServiceList<member>) table.execute().get();
            for (member user : userList) {
                if (user.getId() == id) {
                    listener.onDone(null, user, null);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
    }

    public void getEventList(OnCloudResultListener listener) {
        MobileServiceTable table = sClient.getTable(event.class);
        try {
            listener.onDone((MobileServiceList<event>) table.execute().get(), null, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
    }

    public void getEvent(int id, OnCloudResultListener listener) {
        MobileServiceTable table = sClient.getTable(event.class);
        try {
            MobileServiceList<event> eventList = (MobileServiceList<event>) table.execute().get();
            for (event mEvent : eventList) {
                if (mEvent.getId() == id) {
                    listener.onDone(null, null, mEvent);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
    }

    public void getEvent(double Lat, double Lng, OnCloudResultListener listener) {
        MobileServiceTable table = sClient.getTable(event.class);
        try {
            MobileServiceList<event> eventList = (MobileServiceList<event>) table.execute().get();
            for (event mEvent : eventList) {
                if (mEvent.getLatitude() == Lat && mEvent.getLongitude() == Lng) {
                    listener.onDone(null, null, mEvent);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
    }

    public void createAnEvent(event event) {
        Log.e("lala", "createAnEvent");
        Log.e("lala", "Title:" + event.getTitle());
        Log.e("lala", "Title:" + event.getContent());
        Log.e("lala", "Title:" + event.getTime());
        Log.e("lala", "Title:" + event.getLatitude());
        Log.e("lala", "Title:" + event.getId());
        MobileServiceTable table = sClient.getTable(com.JoinMe.event.class);
//        try {
//            new MobileServiceClient(
//                    "https://join-me.azure-mobile.net/",
//                    "cNsrvVDiQFZLIvfJXQbChGrKlZrckT34", sContext
//            ).getTable(com.JoinMe.event.class).insert(event);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        table.insert(event);
    }
}
