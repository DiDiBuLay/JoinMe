package com.JoinMe;

import com.cloud.JMCloud;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by dante on 2015/10/25.
 */
public class JMEvent implements Serializable {
    private String id;
    private String title = "title";
    private String content = "descripfdsafdsafdsafdsafdsafdsafdsfsdafasdfdsafdsgdsagdsafdsagfdsagdsafdsafdsafdsafdsafsdafdsafdsafdsafsdafsdafdsafsdafdsafdsafdsafsdation";
    private String time = "123";
    private int estimate_num = 5;
    private int current_num = 3;
    private double latitude = 333.5;
    private double longitude = 123.53;
    private String owner_id = "123";

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
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
