package lt.viko.eif.dscerbinkinas.PlanuokBack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto.AuthResponse;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto.RegisterUserDtoRequest;
import lt.viko.eif.dscerbinkinas.PlanuokBack.exception.UserAlreadyExistsException;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.UserRepository;
import lt.viko.eif.dscerbinkinas.PlanuokBack.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private UserAlreadyExistsException userAlreadyExistsException;

    public UserResponseDto addUser(UserRequestDto request) {
        User user = User.builder()

                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .creationDate(OffsetDateTime.now())
                .build();

        userRepository.addUser(user);
        return UserResponseDto.from(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.getAllUsers().stream().map(UserResponseDto::from).collect(Collectors.toList());
    }

    public AuthResponse registerUser(RegisterUserDtoRequest request) {

        if (userRepository.getUserByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        if(userRepository.getUserByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .creationDate(OffsetDateTime.now())
                    .build();

        userRepository.addUser(user);

        String token = jwtUtils.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .email(request.getEmail())
                .build();

    }



}
