package com.monitor.system.dto;

import javax.persistence.*;

/**
 *
 * Created by Srinivas.V on 04-03-2017.
 */
@NamedQueries({
        @NamedQuery(name = "ActiveIPDetails.findAllIpAddressDetails", query ="from ActiveIPDetails activeIPDetails where activeIPDetails.activeIpAddress = ?")
})
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "ACTIVE_IP_DETAILS")
public class ActiveIPDetails extends BaseDto{

    private String activeIpAddress;
    private String date;
    private String activeTime;
    private String lastActiveTime;
    private String deActiveTime;
    private String totalActiveTime;
    private SystemDetails systemDetails;

    @Column(name = "ACTIVE_IP_ADDRESS",nullable = false)
    public String getActiveIpAddress() {
        return activeIpAddress;
    }

    @Column(name = "DATE")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setActiveIpAddress(String activeIpAddress) {
        this.activeIpAddress = activeIpAddress;
    }

    @Column(name = "ACTIVE_TIME")
    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    @Column(name = "LAST_ACTIVE_TIME")
    public String getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    @Column(name = "DEACTIVE_TIME")
    public String getDeActiveTime() {
        return deActiveTime;
    }

    public void setDeActiveTime(String deActiveTime) {
        this.deActiveTime = deActiveTime;
    }

    @Column(name = "TOTAL_ACTIVE_TIME")
    public String getTotalActiveTime() {
        return totalActiveTime;
    }


    public void setTotalActiveTime(String totalActiveTime) {
        this.totalActiveTime = totalActiveTime;
    }

    @ManyToOne
    @JoinColumn(name="SYSTEM_DETAILS_ID")
    public SystemDetails getSystemDetails() {
        return systemDetails;
    }

    public void setSystemDetails(SystemDetails systemDetails) {
        this.systemDetails = systemDetails;
    }

    @Override
    public String toString() {
        return "ActiveIPDetails{" +
                "activeIpAddress='" + activeIpAddress + '\'' +
                ", date='" + date + '\'' +
                ", activeTime='" + activeTime + '\'' +
                ", lastActiveTime='" + lastActiveTime + '\'' +
                ", deActiveTime='" + deActiveTime + '\'' +
                ", totalActiveTime='" + totalActiveTime + '\'' +
                ", systemDetails=" + systemDetails +
                '}';
    }
}
