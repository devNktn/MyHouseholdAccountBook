package dev.nakatani.kakeibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.nakatani.kakeibo.model.SampleModel;
import dev.nakatani.kakeibo.service.SampleService;

@Controller
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "sample/hello", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("message", "Hello Springboot");
        return "sample/hello";
    }

    @RequestMapping(value = "sample/db", method = RequestMethod.GET)
    public String db(Model model) {
        SampleModel sampleModel = sampleService.getSampleModel("5300001");
        model.addAttribute("sampleModel", sampleModel);
        return "sample/db";
    }

    @RequestMapping(value = "sample/contents", method = RequestMethod.GET)
    public String contents(Model model) {
        model.addAttribute("contents", "contents");
        return "sample/contents";
    }

}
