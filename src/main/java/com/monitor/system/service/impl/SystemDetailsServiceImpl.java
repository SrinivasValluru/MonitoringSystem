package com.monitor.system.service.impl;

import com.monitor.system.Constants.StringConstants;
import com.monitor.system.bean.SystemDetailsFrom;
import com.monitor.system.dao.ActiveIPDetailsDao;
import com.monitor.system.dao.SystemDetailsDao;
import com.monitor.system.dto.ActiveIPDetails;
import com.monitor.system.dto.SystemDetails;
import com.monitor.system.service.SystemDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Srinivas.V on 03-03-2017.
 */
@Service("systemDetailsService")
public class SystemDetailsServiceImpl implements SystemDetailsService {

    private static final Logger LOGGER = Logger.getLogger(SystemDetailsServiceImpl.class);

    @Autowired
    SystemDetailsDao systemDetailsDao;
    @Autowired
    ActiveIPDetailsDao activeIPDetailsDao;
    EntityManagerFactory systemEntityManagerFactory;

    @Transactional(value = "systemTransactionManager", propagation = Propagation.REQUIRED)
    public SystemDetails updateSystemDetails(SystemDetailsFrom systemDetailsFrom) throws ParseException {
        SystemDetails systemDetails = new SystemDetails();
        systemDetails.setIpAddress(systemDetailsFrom.getIpAddress());
        systemDetails.setMacAddress(systemDetailsFrom.getMacAddress());
        systemDetails.setGateway(systemDetailsFrom.getGateway());
        systemDetails.setSubnet(systemDetailsFrom.getSubnet());
        systemDetailsDao.create(systemDetails);
        return systemDetails;
    }

    public List<ActiveIPDetails> findAllIpAddressDetails(String ipAddress) {
        return activeIPDetailsDao.findAllIpAddressDetails(ipAddress);
    }

    @Scheduled(cron = "0 */10 * * * ?")
    public void loadAllSystemDetails() {
        EntityManager entityManager = null;
        try {
            entityManager = systemEntityManagerFactory.createEntityManager();
            TransactionSynchronizationManager.bindResource(systemEntityManagerFactory, new EntityManagerHolder(entityManager));
            List<SystemDetails> allSystemDetails = systemDetailsDao.loadAll();
            SimpleDateFormat timeFormat = new SimpleDateFormat(StringConstants.TIME_FORMAT);
            if (allSystemDetails != null) {
                for (SystemDetails systemDetails : allSystemDetails) {
                    List<ActiveIPDetails> activeIPDetailsList = systemDetails.getActiveIPDetailsList();
                    if (activeIPDetailsList != null) {
                        int count = 0;
                        for (ActiveIPDetails loadActiveIPAddress : activeIPDetailsList) {
                            if (null == loadActiveIPAddress.getDeActiveTime()) {
                                count++;
                                if (netIsAvailable(systemDetails.getIpAddress())) {
                                    if (loadActiveIPAddress.getActiveTime() != null) {
                                        Calendar calendar = Calendar.getInstance();
                                        loadActiveIPAddress.setLastActiveTime(timeFormat.format(calendar.getTime()));
                                    }
                                } else {
                                    //deActive time saving in DB
                                    loadActiveIPAddress.setDeActiveTime(timeFormat.format(Calendar.getInstance().getTime()));
                                    try {
                                        Date activeTime = timeFormat.parse(loadActiveIPAddress.getActiveTime());
                                        Date deActiveTime = timeFormat.parse(loadActiveIPAddress.getDeActiveTime());
                                        long difference = deActiveTime.getTime() - activeTime.getTime();
                                        long diffMinutes = difference / (60 * 1000) % 60;
                                        loadActiveIPAddress.setTotalActiveTime(String.valueOf(diffMinutes));
                                        //long diffHours = difference / (60 * 60 * 1000) % 24;
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            activeIPDetailsDao.update(loadActiveIPAddress);
                        }
                        if (count == 0) {
                            createIpAddressDetails(systemDetails);
                        }
                    } else {
                        createIpAddressDetails(systemDetails);
                    }

                }
            }
        } catch (RuntimeException exception) {
            LOGGER.warn("######Exception occured while updating active and deactive through scheduler " + exception.getMessage(), exception);
        } finally {
            if (entityManager != null) {
                TransactionSynchronizationManager.unbindResource(systemEntityManagerFactory);
                EntityManagerFactoryUtils.closeEntityManager(entityManager);
            }
        }
    }

    private static boolean netIsAvailable(String ipAddress) {
        try {
            StringBuilder ipaddress = new StringBuilder();
            ipaddress.append(StringConstants.HTTP).append(ipAddress);
            final URL url = new URL(ipaddress.toString());
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    private void createIpAddressDetails(SystemDetails systemDetails) {
        Format dateFormat = new SimpleDateFormat(StringConstants.DATE_FORMAT);
        SimpleDateFormat timeFormat = new SimpleDateFormat(StringConstants.TIME_FORMAT);
        List<ActiveIPDetails> activeIPDetails = new ArrayList<ActiveIPDetails>();
        ActiveIPDetails activeIPDetail = new ActiveIPDetails();
        activeIPDetail.setActiveIpAddress(systemDetails.getIpAddress());
        activeIPDetail.setDate(dateFormat.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        activeIPDetail.setActiveTime(timeFormat.format(calendar.getTime()));
        activeIPDetail.setSystemDetails(systemDetails);
        activeIPDetails.add(activeIPDetail);
        systemDetails.setActiveIPDetailsList(activeIPDetails);
        systemDetailsDao.update(systemDetails);
    }

    @Resource
    public void setSystemEntityManagerFactory(EntityManagerFactory systemEntityManagerFactory) {
        this.systemEntityManagerFactory = systemEntityManagerFactory;
    }
}
