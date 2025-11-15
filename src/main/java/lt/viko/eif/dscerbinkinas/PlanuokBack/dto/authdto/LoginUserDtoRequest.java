package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.authdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class LoginUserDtoRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
