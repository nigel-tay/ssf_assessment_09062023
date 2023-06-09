package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.Size;

public class AuthForm implements Serializable{

    @Size(min=2, message = "Username should contain 2 or more characters")
    private String username;

    @Size(min=2, message = "Password should contain 2 or more characters")
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

    public static String toJsonString(String username, String password) {
        String loginJson 
            = Json.createObjectBuilder()
                .add("username", username)
                .add("password", password)
                .build()
                .toString();
        return loginJson;
    }
}
