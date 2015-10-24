package com.JoinMe;

import android.app.Application;

import com.cloud.JMCloud;

/**
 * Created by dante on 2015/10/25.
 */
public class JMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JMCloud.createInstance(this);
    }
}
