package com.JoinMe;

import com.cloud.JMCloud;

import java.io.Serializable;

/**
 * Created by dante on 2015/10/25.
 */
public class event implements Serializable {
    private int id;
    private String title = "";
    private String content = "";
    private String time = "";
    private int estimate_num = 0;
    private int current_num = 0;
    private double latitude = 0;
    private double longitude = 0;
    private int owner_id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setEstimate_num(int estimate_num) {
        this.estimate_num = estimate_num;
    }

    public int getEstimate_num() {
        return estimate_num;
    }

    public void setCurrent_num(int current_num) {
        this.current_num = current_num;
    }

    public int getCurrent_num() {
        return current_num;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setOwner_id(int id) {
        owner_id = id;
    }

    public void getOwner(JMCloud.OnCloudResultListener listener) {
        JMCloud cloud = JMCloud.getInstance();
        cloud.getUser(owner_id, listener);
    }
}
