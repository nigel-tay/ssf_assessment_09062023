package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2023.batch3.ssf.frontcontroller.model.AuthForm;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;

@Controller
@RequestMapping
public class LandingController {
    
    @GetMapping()
    public String getLanding(Model m) {
      m.addAttribute("authForm", new AuthForm());
      m.addAttribute("captcha", new Captcha());
      return "view0";
    }
}
