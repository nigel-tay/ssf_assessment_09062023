package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.AuthForm;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping
public class FrontController {
	/*  TODO: Task 2, Task 3, Task 4, Task 6 */

	@Autowired
	AuthenticationService authService;
	
	@PostMapping(
		path="/login", 
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String authenticateLogin(
					@Valid AuthForm loginDetails, 
					BindingResult br, 
					HttpSession session) throws Exception {
		if (br.hasErrors()) {
			return "view0";	
		}
		else {
			authService.authenticate(loginDetails.getUsername(), loginDetails.getPassword());
			return "view0";
		}
	}
}
