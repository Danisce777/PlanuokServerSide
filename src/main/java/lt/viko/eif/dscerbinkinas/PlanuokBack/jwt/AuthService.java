package lt.viko.eif.dscerbinkinas.PlanuokBack.jwt;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto.AuthResponse;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Transactional(readOnly = true)
    public String authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(email, password));

        User user = (User) authentication.getPrincipal();
        return jwtUtils.generateToken(user);
    }

    @Transactional(readOnly = true)
    public AuthResponse authenticateAndBuildResponse(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtUtils.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
