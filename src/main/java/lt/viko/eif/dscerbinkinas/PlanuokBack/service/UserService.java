package lt.viko.eif.dscerbinkinas.PlanuokBack.service;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto addUser(UserRequestDto request) {
        User user = User.builder()

                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .creationDate(OffsetDateTime.now())
                .build();

        userRepository.addUser(user);
        return UserResponseDto.from(user);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.getAllUsers().stream().map(UserResponseDto::from).collect(Collectors.toList());
    }

//    public UserResponseDto updateUser(Long id, UserRequestDto request) {
//
//        Optional<User> existingUser =
//
//
//        User user = User.builder().build()
//                .setUsername(request.getUsername())
//                        .
//
//
//        userRepository.updateUser(user);
//        return UserResponseDto.from(user);
//    }



}
