package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;

public class AuthForm implements Serializable{
    private String username;
    private String password;

    
    public AuthForm(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public AuthForm() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
