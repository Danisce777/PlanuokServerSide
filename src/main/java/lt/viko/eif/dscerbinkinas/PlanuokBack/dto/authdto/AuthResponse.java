package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private String username;
    private String email;
    private String password;
    private Long userId;


}
