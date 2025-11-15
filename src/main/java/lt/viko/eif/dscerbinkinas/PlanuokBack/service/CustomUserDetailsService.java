package lt.viko.eif.dscerbinkinas.PlanuokBack.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.UserRepository;
import org.apache.ibatis.annotations.Arg;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.getUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }


}
