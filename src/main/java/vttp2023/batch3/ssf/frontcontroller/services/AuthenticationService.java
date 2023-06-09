package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vttp2023.batch3.ssf.frontcontroller.model.AuthForm;

@Service
public class AuthenticationService {
	@Value("${ssfassessment.auth.endpoint.url}")
	private String url;

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {
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
			ResponseEntity<String> resp = template.exchange(req, String.class);
			System.out.println(resp.getBody());
			// COME BACK TO THIS LATER
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
}
