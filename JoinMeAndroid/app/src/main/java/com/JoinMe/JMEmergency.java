package com.JoinMe;

import com.cloud.JMCloud;

/**
 * Created by dante on 2015/10/25.
 */
public class JMEmergency {
    private String id;
    private double latitude;
    private double longitude;
    private String owner_id;

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public JMUser getOwner() {
        JMCloud cloud = JMCloud.getInstance();
        return cloud.getUser(owner_id);
    }
}
