package com.faltynka.faltynkaapi.resources;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/alive")
public class HealthCheckResource {

    @CrossOrigin(origins = "http://localhost:8000")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Status alive() {
        Status status = new Status();
        status.setStatus("faltynka-api is alive");
        log.info("faltynka-api is alive");
        return status;
    }
}
