package lt.viko.eif.dscerbinkinas.PlanuokBack.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRequestDto {

    @NotBlank
    @Size(max = 50)
    String username;

    @NotBlank
    @Size(max = 100)
    String password;

    @NotBlank
    @Size(max = 100)
    String email;


}
