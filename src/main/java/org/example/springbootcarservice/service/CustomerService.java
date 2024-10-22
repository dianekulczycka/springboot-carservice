package org.example.springbootcarservice.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootcarservice.dto.auth.SignUpRequestDto;
import org.example.springbootcarservice.dto.auth.SignUpResponseDto;
import org.example.springbootcarservice.entities.Customer;
import org.example.springbootcarservice.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public SignUpResponseDto createCustomer(@Valid SignUpRequestDto signUpRequestDto) {
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());
        Customer customer = new Customer();
        customer.setEmail(signUpRequestDto.getEmail());
        customer.setPassword(password);
        customer.setRole(signUpRequestDto.getRole());
        customer.setRegistrationDate(LocalDateTime.now());
        customerRepository.save(customer);

        return SignUpResponseDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .registrationDate(customer.getRegistrationDate())
                .build();
    }
}
