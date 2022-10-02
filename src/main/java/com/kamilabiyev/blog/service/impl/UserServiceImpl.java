package com.kamilabiyev.blog.service.impl;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.model.entity.User;
import com.kamilabiyev.blog.model.request.LoginRequest;
import com.kamilabiyev.blog.model.request.RefreshTokenRequest;
import com.kamilabiyev.blog.model.request.RegisterRequest;
import com.kamilabiyev.blog.model.response.AuthResponse;
import com.kamilabiyev.blog.repository.UserRepository;
import com.kamilabiyev.blog.security.JWTTokenUtil;
import com.kamilabiyev.blog.service.RoleService;
import com.kamilabiyev.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenUtil tokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticate(request);
        Optional<User> userExists = userRepository.findByUsername(request.getUsername());
        if (userExists.isEmpty()) throw new CustomException("User not found.", HttpStatus.NOT_FOUND);
        var user = userExists.get();
        var accessToken = tokenUtil.generateAccessToken(user);
        var refreshToken = tokenUtil.generateRefreshToken(user);
        var response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        Optional<User> userByUsername = userRepository.findByUsername(request.getUsername());
        if (userByUsername.isPresent())
            throw new CustomException("Username already in use.", HttpStatus.CONFLICT);
        Optional<User> userByEmail = userRepository.findByEmail(request.getEmail());
        if (userByEmail.isPresent())
            throw new CustomException("Email already in use.", HttpStatus.CONFLICT);
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.addRole(roleService.getByName("ROLE_BASIC_USER"));
        userRepository.save(user);
        var accessToken = tokenUtil.generateAccessToken(user);
        var refreshToken = tokenUtil.generateRefreshToken(user);
        var response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String username = tokenUtil.getUsernameFromToken(request.getRefreshToken());
        Optional<User> userByUsername = userRepository.findByUsername(username);
        if (userByUsername.isEmpty())
            throw new CustomException("Token is not valid.", HttpStatus.NOT_FOUND);
        User user = userByUsername.get();
        var accessToken = tokenUtil.generateAccessToken(user);
        var refreshToken = tokenUtil.generateRefreshToken(user);
        var response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }

    private void authenticate(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (DisabledException e) {
            throw new DisabledException("User is disabled", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Bad credentials", e);
        } catch (LockedException e) {
            throw new LockedException("Account is locked", e);
        }
    }
}
