package com.monitor.system.controller;

import com.monitor.system.bean.LoadIpForm;
import com.monitor.system.bean.LoadIpValidator;
import com.monitor.system.dto.ActiveIPDetails;
import com.monitor.system.service.SystemDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by ADMIN on 07-03-2017.
 */
@Controller
public class LoadIpAddressController {

    private static final Logger LOGGER = Logger.getLogger(LoadIpAddressController.class);

    @Autowired
    LoadIpValidator loadIpValidator;
    @Autowired
    SystemDetailsService systemDetailsService;

    @InitBinder
    protected void initBin0der(WebDataBinder binder) {
        binder.setValidator(loadIpValidator);
    }

    @RequestMapping(value = "/loadIpAddress", method = RequestMethod.GET)
    public ModelAndView loadIpView() {
        return new ModelAndView("loadIpAddress", "loadIpCommandName", new LoadIpForm());
    }

    @RequestMapping(value = "/loadIpAddress", method = RequestMethod.POST)
    public ModelAndView loadIpAddressDetails(@ModelAttribute("loadIpCommandName") @Valid LoadIpForm loadIpForm, BindingResult bindingResult, HttpServletRequest request) {
        List<ActiveIPDetails> activeIPDetailsList = systemDetailsService.findAllIpAddressDetails(loadIpForm.getLoadIpAddress());
        if (bindingResult.hasErrors()) {
            return new ModelAndView("loadIpAddress");
        } else {
            return new ModelAndView("loadIpDetails", "activeIPDetailsList", activeIPDetailsList);
        }
    }
}
