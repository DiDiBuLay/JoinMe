package com.JoinMe;

import com.cloud.JMCloud;

import java.io.Serializable;

/**
 * Created by dante on 2015/10/25.
 */
public class emergency implements Serializable {
    private int id;
    private double latitude;
    private double longitude;
    private int owner_id;

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void getOwner(JMCloud.OnCloudResultListener listener) {
        JMCloud cloud = JMCloud.getInstance();
        cloud.getUser(owner_id, listener);
    }
}
