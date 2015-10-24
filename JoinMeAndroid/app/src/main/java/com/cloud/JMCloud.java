package com.cloud;

import android.content.Context;

import com.JoinMe.JMUser;
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
    private static JMCloud sCloud;
    private static Context sContext;

    private static MobileServiceClient sClient;

    public static JMCloud createInstance(Context context) {
        sContext = context;
        sCloud = new JMCloud();
        return sCloud;
    }

    public static JMCloud getInstance() {
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

    public MobileServiceList<JMUser> getUserList() {
        MobileServiceTable table = sClient.getTable(JMUser.class);
        try {
            return (MobileServiceList<JMUser>) table.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (MobileServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JMUser getUser(String id) {
        MobileServiceList<JMUser> userList = getUserList();
        for (JMUser user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
