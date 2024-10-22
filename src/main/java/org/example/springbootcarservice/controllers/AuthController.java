package org.example.springbootcarservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootcarservice.dto.auth.AuthRequestDto;
import org.example.springbootcarservice.dto.auth.AuthResponseDto;
import org.example.springbootcarservice.dto.auth.SignUpRequestDto;
import org.example.springbootcarservice.dto.auth.SignUpResponseDto;
import org.example.springbootcarservice.service.CustomerService;
import org.example.springbootcarservice.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = customerService.createCustomer(signUpRequestDto);
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDto> signInBuyer(@RequestBody @Valid AuthRequestDto authRequestDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
                (authRequestDto.getEmail(), authRequestDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);

        if (auth.isAuthenticated()) {
            UserDetails customer = customerService.loadUserByUsername(authRequestDto.getEmail());
            String accessToken = jwtUtil.generateAccessToken(customer);
            String refreshToken = jwtUtil.generateRefreshToken(customer);
            return new ResponseEntity<>(AuthResponseDto
                    .builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
