package com.smartosc.team5.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * team5
 *
 * @author Huupd
 * @created_at 10/06/2020 - 2:39 PM
 * @created_by Huupd
 */
@RestController
class SwaggerRedirectController {

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public ModelAndView redirect1() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @RequestMapping(value = "/ui", method = RequestMethod.GET)
    public ModelAndView redirect2() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }

    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public ModelAndView redirect3() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }
}
