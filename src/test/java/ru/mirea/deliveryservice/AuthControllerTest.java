package ru.mirea.deliveryservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import ru.mirea.deliveryservice.controllers.AuthController;
import ru.mirea.deliveryservice.dto.JwtAuthenticationResponse;
import ru.mirea.deliveryservice.dto.SignInRequest;
import ru.mirea.deliveryservice.dto.SignUpRequest;
import ru.mirea.deliveryservice.services.AuthenticationService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @Mock
    private AuthenticationService authenticationService;
    @InjectMocks
    private AuthController authController;

    @Test
    public void signUpTest() {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("email");
        signUpRequest.setPassword("password");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("token", "USER");

        when(authenticationService.signUp(any())).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authController.signUp(signUpRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtAuthenticationResponse, responseEntity.getBody());
    }

    @Test
    public void signInTest() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail("email");
        signInRequest.setPassword("password");
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse("token", "USER");

        when(authenticationService.signIn(any())).thenReturn(jwtAuthenticationResponse);

        ResponseEntity<JwtAuthenticationResponse> responseEntity = authController.signIn(signInRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtAuthenticationResponse, responseEntity.getBody());
    }
}