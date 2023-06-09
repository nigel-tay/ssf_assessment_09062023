package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Captcha implements Serializable{
    private boolean captchaFailed = false;
    private String number1;
    private String number2;
    private String operation;
    private String answer;
    

    public boolean isCaptchaFailed() {
        return captchaFailed;
    }
    public void setCaptchaFailed(boolean captchaFailed) {
        this.captchaFailed = captchaFailed;
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Captcha() {
        final ArrayList<String> operations = 
                new ArrayList<>(Arrays.asList("+", "-", "/", "*"));
        this.number1 = Integer.toString((int) Math.round(Math.random() * 50 + 1));
        this.number2 = Integer.toString((int) Math.round(Math.random() * 50 + 1));
        this.operation = operations.get((int) (System.currentTimeMillis() % operations.size()));
    }
    public Captcha(String number1, String number2, String operation, String answer, boolean captchaFailed) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
        this.answer = answer;
        this.captchaFailed = captchaFailed;
    }

    public String getNumber1() {
        return number1;
    }
    public void setNumber1(String number1) {
        this.number1 = number1;
    }
    public String getNumber2() {
        return number2;
    }
    public void setNumber2(String number2) {
        this.number2 = number2;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
}
