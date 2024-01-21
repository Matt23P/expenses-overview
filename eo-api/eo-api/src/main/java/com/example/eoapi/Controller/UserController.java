package com.example.eoapi.Controller;

import com.example.eoapi.Request.CreateUserRequest;
import com.example.eoapi.Request.LoginRequest;
import com.example.eoapi.Response.LoginResponse;
import com.example.eoapi.Response.StatusMessage;
import com.example.eoapi.Service.UserService;
import com.example.eoapi.Utils.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("${url.api.user}")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RequestValidator validator;
    private final Logger logger = Logger.getLogger(getClass().getName());
    @Value("${url.api.user}")
    private String userControllerUrl;

    @PostMapping(path = "/signup")
    public ResponseEntity<StatusMessage> signUpUser(@RequestBody CreateUserRequest request) {
        boolean flag = validator.validateSignUpRequest(request);
        if (flag) {
            StatusMessage response = userService.signUpUser(request);
            return ResponseEntity.ok(response);
        } else {
            logger.log(Level.WARNING, userControllerUrl + "/signup : 400 BAD REQUEST : " + request.toString());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        boolean flag = validator.validateLoginRequest(request);
        if (flag) {
            LoginResponse response = userService.loginUser(request);
            return ResponseEntity.ok(response);
        } else {
            logger.log(Level.WARNING, userControllerUrl + "/login : 400 BAD REQUEST : " + request.toString());
            return ResponseEntity.badRequest().build();
        }
    }
}
