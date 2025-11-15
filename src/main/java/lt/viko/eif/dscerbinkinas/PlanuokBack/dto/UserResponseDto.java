package lt.viko.eif.dscerbinkinas.PlanuokBack.dto;


import lombok.Builder;
import lombok.Value;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;

@Value
@Builder
public class UserResponseDto {

    Long id;
    String username;
    String password;
    String email;

    //Mapper

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }



}
