package com.example.eoapi.Controller;

import com.example.eoapi.Request.CreateUserRequest;
import com.example.eoapi.Response.StatusMessage;
import com.example.eoapi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("eo/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<StatusMessage> signUpUser(@RequestBody CreateUserRequest request) {
        StatusMessage response = userService.signUpUser(request);
        return ResponseEntity.ok(response);
    }
}
