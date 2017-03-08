package com.monitor.system.bean;

import java.io.Serializable;

/**
 *
 * Created by ADMIN on 07-03-2017.
 */
public class LoadIpForm implements Serializable{

    private String loadIpAddress;

    public String getLoadIpAddress() {
        return loadIpAddress;
    }

    public void setLoadIpAddress(String loadIpAddress) {
        this.loadIpAddress = loadIpAddress;
    }
}
