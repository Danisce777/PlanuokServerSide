package lt.viko.eif.dscerbinkinas.PlanuokBack.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto.AuthResponse;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto.LoginUserDtoRequest;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto.RegisterUserDtoRequest;
import lt.viko.eif.dscerbinkinas.PlanuokBack.jwt.AuthService;
import lt.viko.eif.dscerbinkinas.PlanuokBack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse registerUser(@Valid @RequestBody RegisterUserDtoRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginUserDtoRequest request) {
        return authService.authenticateAndBuildResponse(request.getEmail(), request.getPassword());
    }

}
