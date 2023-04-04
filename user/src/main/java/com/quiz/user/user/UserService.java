package com.quiz.user.user;

import com.quiz.user.dto.LoginDTO;
import com.quiz.user.dto.RegisterDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(RegisterDTO dto) {
        User checkUser = getUserByEmail(dto.email());

        if (checkUser != null) {
            log.error("korisnik {} vec postoji", checkUser.getEmail());
            return null;
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);
        user.setRegistrationDate(LocalDateTime.now());

        return userRepository.save(user);
    }

    public void updateUserStatus(Integer userId, UserStatus status) {
        User user = getUserById(userId);
        user.setStatus(status);
        userRepository.save(user);
    }

    public Boolean checkCredentials(LoginDTO loginDTO) {
        User user = getUserByEmail(loginDTO.email());

        if (user != null && passwordEncoder.matches(loginDTO.password(), user.getPassword()))
            return true;

        return false;
    }
}
