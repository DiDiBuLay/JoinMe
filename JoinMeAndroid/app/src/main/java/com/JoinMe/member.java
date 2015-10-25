package com.JoinMe;

import java.io.Serializable;

/**
 * Created by dante on 2015/10/25.
 */
public class member implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
