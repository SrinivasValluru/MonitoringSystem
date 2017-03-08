package com.monitor.system.dto;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * Created by Srinivas.V on 28-02-2017.
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "SYSTEM_DETAILS")
public class SystemDetails extends BaseDto {

    private String ipAddress;

    private String macAddress;

    private String gateway;

    private String subnet;

    private List<ActiveIPDetails> activeIPDetailsList;

    @Column(name = "IP_ADDRESS",nullable = false)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Column(name = "MAC_ADDRESS",nullable = false)
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Column(name = "GATEWAY",nullable = false)
    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Column(name = "SUBNET",nullable = false)
    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    @OneToMany(mappedBy = "systemDetails",cascade = CascadeType.ALL)
    public List<ActiveIPDetails> getActiveIPDetailsList() {
        return activeIPDetailsList;
    }

    public void setActiveIPDetailsList(List<ActiveIPDetails> activeIPDetailsList) {
        this.activeIPDetailsList = activeIPDetailsList;
    }

    @Override
    public String toString() {
        return "SystemDetails{" +
                "ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", gateway='" + gateway + '\'' +
                ", subnet='" + subnet + '\'' +
                '}';
    }
}
