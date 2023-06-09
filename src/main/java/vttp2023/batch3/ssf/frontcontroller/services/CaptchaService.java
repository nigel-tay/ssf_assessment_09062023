package vttp2023.batch3.ssf.frontcontroller.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import vttp2023.batch3.ssf.frontcontroller.model.AuthForm;

@Service
public class CaptchaService {
    public List<ObjectError> validateCaptcha(AuthForm authForm) {
        List<ObjectError> errors = new LinkedList<>();
        FieldError error;
        if (authForm.getFailedCount() > 0) {
            error = new FieldError("authForm", "captcha", 
                    "You have failed the captcha, you have failed %s times"
                        .formatted(Integer.toString(authForm.getFailedCount())));
            errors.add(error);
        }
        return errors;
    }
}
