package com.chitcheck.auth.service;

import com.chitcheck.auth.dto.AuthRequest;
import com.chitcheck.auth.dto.AuthResponse;
import com.chitcheck.auth.dto.ChangePasswordRequest;
import com.chitcheck.auth.dto.RegisterRequest;
import com.chitcheck.auth.dto.VerifyRequest;
import com.chitcheck.auth.model.Role;
import com.chitcheck.auth.model.User;
import com.chitcheck.auth.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    // Manual constructor to replace @RequiredArgsConstructor
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }

    public String register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent() || userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("User with given username or email already exists.");
        }

        String otp = generateOtp();
        User user = new User();
        user.setCompanyName(request.getCompanyName());
        user.setEmail(request.getEmail());
        user.setMobileNo(request.getMobileNo());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        user.setEnabled(false);

        userRepository.save(user);

        emailService.sendOtpEmail(request.getEmail(), otp);

        return "User registered successfully. Please check your email for the OTP.";
    }

    public AuthResponse verifyAccount(VerifyRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email: " + request.getEmail()));

        if (user.getOtp().equals(request.getOtp()) && user.getOtpGeneratedTime().plusMinutes(5).isAfter(LocalDateTime.now())) {
            user.setEnabled(true);
            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);
            return new AuthResponse(jwtToken);
        }

        throw new IllegalStateException("Invalid or expired OTP.");
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public void changePassword(ChangePasswordRequest request, User user) {
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}