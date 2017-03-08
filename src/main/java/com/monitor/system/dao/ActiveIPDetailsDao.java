package com.monitor.system.dao;

import com.monitor.system.dto.ActiveIPDetails;

import java.util.List;

/**
 *
 * Created by Srinivas.V on 04-03-2017.
 */
public interface ActiveIPDetailsDao extends GenericDao<ActiveIPDetails, Long>{

    List<ActiveIPDetails> findAllIpAddressDetails(String ipAddress);
}
