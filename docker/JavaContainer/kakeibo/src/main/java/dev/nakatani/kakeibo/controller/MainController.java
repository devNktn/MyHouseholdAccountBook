package dev.nakatani.kakeibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.nakatani.kakeibo.model.SampleModel;
import dev.nakatani.kakeibo.service.SampleService;

@Controller
public class MainController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("message", "Hello Springboot");
        return "hello";
    }

    @RequestMapping(value = "/dbsample", method = RequestMethod.GET)
    public String db(Model model) {
        SampleModel sampleModel = sampleService.getSampleModel("5300001");
        model.addAttribute("sampleModel", sampleModel);
        return "dbsample";
    }
}