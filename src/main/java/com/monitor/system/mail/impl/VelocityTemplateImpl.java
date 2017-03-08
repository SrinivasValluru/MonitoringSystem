package com.monitor.system.mail.impl;

import com.monitor.system.mail.VelocityTemplate;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Anish
 * Date: 4/26/14
 * Time: 10:08 PM
 */
@Service
public class VelocityTemplateImpl implements VelocityTemplate {

    private VelocityEngine velocityEngine;
    private static final Logger LOGGER = Logger.getLogger(VelocityTemplateImpl.class);

    public String prepareStringFromVelocityTemplate(String vmFileLocation, Object... data) {
        Map<String, Object> map = new HashMap<String, Object>();
        for(Object object : data) {
            LOGGER.debug(object.getClass());
            map.put(WordUtils.uncapitalize(object.getClass().getSimpleName()), object);
        }
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmFileLocation, map);
    }

    @Resource
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
