package com.monitor.system.bean;

import com.monitor.system.dto.SystemDetails;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 *
 * Created by Srinivas.V on 03-03-2017.
 */
@Component
public class SystemDetailsValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger(SystemDetailsValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return SystemDetailsFrom.class == clazz;
    }

    /**
     * This validate method to handle the login validations.
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        SystemDetailsFrom systemDetailsFrom = (SystemDetailsFrom) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ipAddress", "NotEmpty.systemDetailsFrom.ipAddress");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "macAddress", "NotEmpty.systemDetailsFrom.macAddress");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gateway", "NotEmpty.systemDetailsFrom.gateway");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subnet", "NotEmpty.systemDetailsFrom.subnet");

    }
}
