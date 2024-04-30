package com.newlecmineursprj.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("error")
public class ErrorController {

    @GetMapping
    public String error() {

        return "error";
    }
}