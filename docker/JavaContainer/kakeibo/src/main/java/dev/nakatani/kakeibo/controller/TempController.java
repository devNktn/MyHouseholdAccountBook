package dev.nakatani.kakeibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TempController {

    @RequestMapping(value = "/temp/mytemplate", method = RequestMethod.GET)
    public String mytemplate(Model model) {
        model.addAttribute("temp", "テンプレートです");
        return "temp/mytemplate";
    }

}
