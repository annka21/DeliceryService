package ru.mirea.deliveryservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mirea.deliveryservice.config.JwtService;
import ru.mirea.deliveryservice.dto.JwtAuthenticationResponse;
import ru.mirea.deliveryservice.dto.SignInRequest;
import ru.mirea.deliveryservice.dto.SignUpRequest;
import ru.mirea.deliveryservice.models.Role;
import ru.mirea.deliveryservice.models.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .address(request.getAddress())
                .telephoneNumber(request.getTelephoneNumber())
                .role(Role.USER)
                .build();

        userService.create(user);

        String token = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(token, user.getRole().toString());
    }

    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(token, ((User)user).getRole().toString());
    }
}
