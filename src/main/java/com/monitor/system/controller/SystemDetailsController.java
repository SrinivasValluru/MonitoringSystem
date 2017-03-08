package com.monitor.system.controller;

import com.monitor.system.bean.SystemDetailsFrom;
import com.monitor.system.bean.SystemDetailsValidator;
import com.monitor.system.dto.SystemDetails;
import com.monitor.system.service.SystemDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;

/**
 * Created by Srinivas.V on 03-03-2017.
 */
@Controller
public class SystemDetailsController {

    private static final Logger LOGGER = Logger.getLogger(SystemDetailsController.class);

    @Autowired
    SystemDetailsValidator validator;
    @Autowired
    SystemDetailsService detailsService;

    /**
     * This method to bind the properties
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * This method
     *
     * @return
     */
    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public ModelAndView systemDetails() {
        return new ModelAndView("system", "systemCommandName", new SystemDetailsFrom());
    }
    /**
     * This method handle the user login request
     *
     * @param bindingResult
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "systemDetailsBean", method = RequestMethod.POST)
    public ModelAndView systemDetailsPage(@ModelAttribute("systemCommandName") @Valid SystemDetailsFrom systemDetailsFrom, BindingResult bindingResult, HttpServletRequest request) throws ParseException {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("system");
        } else {
            try {
                SystemDetails systemDetails = detailsService.updateSystemDetails(systemDetailsFrom);
                if (systemDetails != null) {
                    request.setAttribute("systemDetails", "IP details saved successfully...");
                }
            } catch (Exception e) {
                bindingResult.rejectValue("Whoops!", "Whoops! error occured due to techical problem,try again.");
            }
            return new ModelAndView("system");
        }
    }
}
