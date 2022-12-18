package com.jk.notes.service;


import com.jk.notes.exception.RoleNotFoundException;
import com.jk.notes.exception.UsernameAlreadyExistsException;
import com.jk.notes.payload.request.LoginRequest;
import com.jk.notes.payload.request.SignupRequest;
import com.jk.notes.payload.response.JwtResponse;
import com.jk.notes.payload.response.MessageResponse;
import com.jk.notes.repository.RoleRepository;
import com.jk.notes.repository.UserRepository;
import com.jk.notes.security.jwt.JwtUtils;
import com.jk.notes.security.services.UserDetailsImpl;
import com.jk.notes.model.user.User;
import com.jk.notes.model.user.role.ERole;
import com.jk.notes.model.user.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;


    public JwtResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponse(jwt,
                userDetails.id(),
                userDetails.getUsername(),
                roles.get(0));
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(signUpRequest.getUsername());
        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .lastName(signUpRequest.getLastName())
                .role(getRole())
                .build();

        userRepository.save(user);
        return new MessageResponse(String.format("User %s has been registered successfully.", user.getUsername()));
    }

    private Role getRole() {
        return roleRepository.findByName(ERole.ROLE_REGISTERED_USER)
                .orElseThrow(() -> new RoleNotFoundException(ERole.ROLE_REGISTERED_USER.name()));
    }
}
