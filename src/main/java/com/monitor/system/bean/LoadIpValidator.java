package com.monitor.system.bean;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by ADMIN on 07-03-2017.
 */
@Component
public class LoadIpValidator implements Validator {

    private static final Logger LOGGER = Logger.getLogger(LoadIpValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return LoadIpForm.class == clazz;
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoadIpForm loadIpForm = (LoadIpForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loadIpAddress", "NotEmpty.loadIpForm.loadIpAddress");
    }
}
