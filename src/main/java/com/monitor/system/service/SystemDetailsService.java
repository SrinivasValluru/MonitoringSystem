package com.monitor.system.service;

import com.monitor.system.bean.SystemDetailsFrom;
import com.monitor.system.dto.ActiveIPDetails;
import com.monitor.system.dto.SystemDetails;

import java.text.ParseException;
import java.util.List;

/**
 *
 * Created by Srinivas on 03-03-2017.
 */
public interface SystemDetailsService {

    SystemDetails updateSystemDetails(SystemDetailsFrom systemDetailsFrom) throws ParseException;

    List<ActiveIPDetails> findAllIpAddressDetails(String ipAddress);

}


