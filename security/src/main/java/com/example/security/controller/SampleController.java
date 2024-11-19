package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sample")
@Log4j2
@Controller
public class SampleController {

    @GetMapping("/guest")
    public void getGuest() {
        log.info("guest 요청");
    }

    @GetMapping("/member")
    public void getMEMBER() {
        log.info("member 요청");
    }

    @GetMapping("/admin")
    public void getADMIN() {
        log.info("admin 요청");
    }

}