package com.quiz.user.controler;

import com.quiz.user.dto.LoginDTO;
import com.quiz.user.dto.RegisterDTO;
import com.quiz.user.user.User;
import com.quiz.user.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterDTO dto) {
        return userService.saveUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>("neispranvi kredencijali",HttpStatus.BAD_REQUEST);
    }

}
