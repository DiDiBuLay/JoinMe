package com.JoinMe;

import com.cloud.JMCloud;

import java.sql.Date;

/**
 * Created by dante on 2015/10/25.
 */
public class JMEvent {
    private String id;
    private String title;
    private String content;
    private Date start_time;
    private Date end_time;
    private int estimate_num;
    private int current_num;
    private double latitude;
    private double longitude;
    private String owner_id;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public int getEstimate_num() {
        return estimate_num;
    }

    public int getCurrent_num() {
        return current_num;
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
