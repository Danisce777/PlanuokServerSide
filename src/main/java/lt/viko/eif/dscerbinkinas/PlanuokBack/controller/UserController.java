package lt.viko.eif.dscerbinkinas.PlanuokBack.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.constants.ApiConstants;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.USERS)
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto addUser(@Valid @RequestBody UserRequestDto request) {
        return userService.addUser(request);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

}
