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
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping
public class FrontController {
	/*  TODO: Task 2, Task 3, Task 4, Task 6 */

	boolean authenticated = false; //TEMPORARY VARIABLE PLEASE DELETE
	@Autowired
	AuthenticationService authService;

	@Autowired
	AuthenticationRepository authRepo;
	
	@PostMapping(
		path="/login", 
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String authenticateLogin(
					@Valid AuthForm authForm, 
					BindingResult br, 
					Captcha captcha,
					HttpSession session,
					Model m) throws Exception {
		if (br.hasErrors()) {
			if (session.getAttribute("captchaExists").toString().equals("true")) {
				captcha.setCaptchaFailed(true);
				return "view0";
			}
			m.addAttribute("captcha", captcha);
			m.addAttribute("authForm", authForm);
			return "view0";	
		}
		if (authRepo.checkUserDisabled(authForm.getUsername())) { // is user is not disabled
			authenticated = authService.authenticate(authForm.getUsername(), authForm.getPassword());
			if (authenticated) {
				session.setAttribute("authenticated", true);
				session.removeAttribute("sessionFailedCount");
				return "view1";
			}
			else { // FIRST FAILED ATTEMPT
				int failCount = session.getAttribute("sessionFailedCount") == null
							? 1
							: Integer.parseInt(session.getAttribute("sessionFailedCount").toString());
				if (failCount != 1) {
					session.removeAttribute("sessionFailedCount");
					session.setAttribute("sessionFailedCount", Integer.toString(failCount++));
					System.out.println(session.getAttribute("sessionFailedCount"));
					if (authService.evaluateCaptcha(captcha)) {
						session.setAttribute("authenticated", true);
						session.removeAttribute("sessionFailedCount");
						return "view1";
					}
				}
				session.setAttribute("sessionFailedCount", "1");
				session.setAttribute("captchaExists", "true");
				captcha.setCaptchaFailed(true);
				m.addAttribute("captcha", captcha);
				return "view0";
			// 	System.out.println("LAYER 1");
			// 	System.out.println(session.getAttribute("sessionFailedCount"));
			// 	String failCount = session.getAttribute("sessionFailedCount") == null
			// 										? "1" 
			// 										: session.getAttribute("sessionFailedCount").toString();
			// 	session.setAttribute("sessionFailedCount", failCount);
			// 	session.setAttribute("captchaExists", "true");
			// 	if (Integer.parseInt(failCount) != 3) { // ALLOW RETRY UP TO 3
			// 		// authService.authenticate(authForm.getUsername(), authForm.getPassword());
			// 		authenticated = false;
			// 		if (authenticated) {
			// 			session.setAttribute("authenticated", true);
			// 			authForm.setFailedCount(0);
			// 			return "view1";
			// 		}
			// 		else {
			// 			System.out.println("LAYER 2");
			// 			int tempCount = Integer.parseInt(failCount);
			// 			session.removeAttribute("sessionFailedCount");
			// 			session.setAttribute("sessionFailedCount", Integer.toString(tempCount++));
			// 			System.out.println(Integer.toString(tempCount++));
			// 			System.out.println((String)session.getAttribute("sessionFailedCount"));
			// 			m.addAttribute("captcha", captcha);
			// 			m.addAttribute("authForm", authForm);
			// 			return "view0";
			// 		}
			// 	}
			// 	else {
			// 		System.out.println("LAYER 3");
			// 		authService.disableUser(authForm.getUsername());
			// 		m.addAttribute("captcha", captcha);
			// 		m.addAttribute("authForm", authForm);
			// 		return "view0";
			// 	}
			}
		}
		else {
			System.out.println("LAYER 4");
			authForm.setDisabled(true);
			m.addAttribute("authForm", authForm);
			return "view2";
		}
	}
}
