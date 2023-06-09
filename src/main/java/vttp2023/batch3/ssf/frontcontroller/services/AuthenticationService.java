package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp2023.batch3.ssf.frontcontroller.model.AuthForm;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;

@Service
public class AuthenticationService {
	@Autowired
	AuthenticationRepository authRepo;

	@Value("${ssfassessment.auth.endpoint.url}")
	private String url;

	private String correctAnswer = "";

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public boolean authenticate(String username, String password) throws Exception {
		String loginJsonString = AuthForm.toJsonString(username, password);
		System.out.println(url);
		System.out.println(loginJsonString);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");

		RequestEntity<String> req = RequestEntity
			.post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.headers(headers)
			.body(loginJsonString, String.class);
			RestTemplate template = new RestTemplate();
			try {
				ResponseEntity<String> resp = template.exchange(req, String.class);
				System.out.println(resp.getBody());
				System.out.println(resp.getStatusCode().toString());
				if (resp.getStatusCode().toString().equals("201 CREATED")) {
					authRepo.
					return true;
				}
				else {
					return false;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}

	public boolean evaluateCaptcha(Captcha captcha) {
		final int num1 = Integer.parseInt(captcha.getNumber1());
		final int num2 = Integer.parseInt(captcha.getNumber2());
		final String operation = captcha.getOperation();
		final String answer = captcha.getAnswer();

		switch(operation) {
			case "+":
				correctAnswer = Integer.toString(num1 + num2);
				break;
			case "-":
				correctAnswer = Integer.toString(num1 - num2);
				break;
			case "*":
				correctAnswer = Integer.toString(num1 * num2);
				break;
			case "/":
				correctAnswer = Integer.toString(num1 / num2);
				break;
		}

		if (correctAnswer.equals(answer)) {
			return true;
		}
		else {
			return false;
		}

	}
}
