package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDtoRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
