package com.monitor.system.bean;

import java.io.Serializable;

/**
 *
 * Created by Srinivas.V on 01-03-2017.
 */
public class SystemDetailsFrom implements Serializable {

    private String ipAddress;

    private String macAddress;

    private String gateway;

    private String subnet;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

}
