package com.quiz.user.controler;

import com.quiz.user.dto.LoginDTO;
import com.quiz.user.dto.RegisterDTO;
import com.quiz.user.security.JwtUtil;
import com.quiz.user.user.User;
import com.quiz.user.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO dto) {
        if (userService.saveUser(dto) == null)
            return ResponseEntity.badRequest().body("Korisnik sa tom adresom vec postoji");

        return ResponseEntity.ok("Korisnik uspjesno registrovan");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        if (userService.checkCredentials(loginDTO))
            return new ResponseEntity<>(jwtUtil.generateToken(loginDTO.email()), HttpStatus.OK);

        return new ResponseEntity<>("neispranvi kredencijali", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/validateToken")
    public Boolean validateToken(@RequestBody String token) {
        log.info("validacija...");
        return jwtUtil.validateToken(token);
    }

}
