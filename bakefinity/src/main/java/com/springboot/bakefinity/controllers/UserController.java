package com.springboot.bakefinity.controllers;


import com.springboot.bakefinity.mappers.AddressMapper;
import com.springboot.bakefinity.mappers.UserMapper;
import com.springboot.bakefinity.model.dtos.LoginRequestDto;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.dtos.UserRegistrationRequestDTO;
import com.springboot.bakefinity.services.interfaces.UserInterestsService;
import com.springboot.bakefinity.services.interfaces.UserLoginService;
import com.springboot.bakefinity.services.interfaces.UserRegisterService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserLoginService userAuthentication;
    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper  addressMapper;
    @Autowired
    private UserInterestsService userInterestsService;



    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequestDTO request) {
        userRegisterService.createUser(request);
        return ResponseEntity.ok("User registered successfully!");
    }


    /******************* user Login Api ************************/
    //http://localhost:8080/users/login    >> email,password   post
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        UserDTO userDto = userAuthentication.login(loginRequestDto, session);
        return ResponseEntity.ok(userDto);
    }

    /******************* user logout Api ************************/
    //http://localhost:8080/users/logout     post
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        userAuthentication.logout(session);
        return ResponseEntity.ok("Logged out successfully.");
    }

    /*********************** For Test *******************************/
    //http://localhost:8080/users/check-session    Get
    @GetMapping("/check-session")
    public ResponseEntity<?> checkSession(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok("Session is active. User: " + user.toString());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No active session.");
        }
    }

    // Check Username Availability
    // GET /users/check-username?username=salma
    @GetMapping("/check-username")
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        boolean exists = userRegisterService.isUsernameAvailable(username);
        if (exists) {
            return ResponseEntity.ok("Username is already taken.");
        } else {
            return ResponseEntity.ok("Username is available.");
        }
    }

    // Check Email Uniqueness
    //GET /users/check-email?email=salma@example.com
    @GetMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        boolean exists = userRegisterService.isEmailUnique(email);
        if (exists) {
            return ResponseEntity.ok("Email is already registered.");
        } else {
            return ResponseEntity.ok("Email is available.");
        }
    }

}
