package co.com.personalsoft.jwtexample.services;

import co.com.personalsoft.jwtexample.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No se encontro el nombre de usuario "
                                + username));
        return UserDetailsImpl.build(user);
    }
}
