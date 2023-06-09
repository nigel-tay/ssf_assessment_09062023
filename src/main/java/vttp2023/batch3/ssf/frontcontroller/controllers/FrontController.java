package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.AuthForm;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping
public class FrontController {
	/*  TODO: Task 2, Task 3, Task 4, Task 6 */
	private int captchaCount = 0;

	@Autowired
	AuthenticationService authService;
	
	@PostMapping(
		path="/login", 
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String authenticateLogin(
					@Valid AuthForm authForm, 
					BindingResult br, 
					Captcha captcha,
					HttpSession session,
					Model m) {
		if (br.hasErrors()) {
			System.out.println("HEREEEEEEEEEEEEE");
			return "view0";	
		}
		else {
			// authService.authenticate(authForm.getUsername(), authForm.getPassword());
			boolean authenticated = false;
			if (authenticated) {
				session.setAttribute("authenticated", true);
				return "view1";
			}
			else {
				captcha.setCaptchaFailed(true); // display captcha
				m.addAttribute("captcha", captcha);
				m.addAttribute("authForm", authForm);
				return "view0";
			}
		}
	}
}
