package com.faltynka.faltynkaapi.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/alive")
public class HealthCheckResource {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String alive() {
        log.info("faltynka-api is alive");
        return "faltynka-api is alive";
    }
}
