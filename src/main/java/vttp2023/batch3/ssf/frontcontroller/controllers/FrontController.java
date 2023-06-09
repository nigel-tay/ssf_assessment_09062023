package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2023.batch3.ssf.frontcontroller.model.AuthForm;

@Controller
@RequestMapping
public class FrontController {
	
	// TODO: Task 2, Task 3, Task 4, Task 6
	@PostMapping(path="/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String authenticateLogin(AuthForm loginDetails) {
		System.out.println(loginDetails.getUsername() + " " + loginDetails.getPassword());
		return "view0";
	}
}
