package lt.viko.eif.dscerbinkinas.PlanuokBack.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String email;
    private OffsetDateTime creationDate;

}
