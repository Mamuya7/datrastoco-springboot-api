package com.mamuya.datrastocospringbootapi.auth.authService;

import com.mamuya.datrastocospringbootapi.auth.authEntity.AuthUserDetails;
import com.mamuya.datrastocospringbootapi.auth.authRepository.AuthUserDetailsRepository;
import com.mamuya.datrastocospringbootapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private AuthUserDetailsRepository authUserDetailsRepository;

    @Autowired
    public AuthUserDetailsService(AuthUserDetailsRepository authUserDetailsRepository) {
        this.authUserDetailsRepository = authUserDetailsRepository;
    }

    @Transactional
    public User findByUsername(String username){
        Optional<User> optionalUser = authUserDetailsRepository.findByUsername(username);
        return optionalUser.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = this.findByUsername(s);

        Set<SimpleGrantedAuthority> authorities = null;

        if(user.getRole() != null){

            authorities = user.getRole()
                    .getPermissions()
                    .stream()
                    .map(p -> new SimpleGrantedAuthority(
                            p.getEntity() + ":" + p.getType())
                    )
                    .collect(Collectors.toSet());

            authorities.add(
                    new SimpleGrantedAuthority(
                            "ROLE_" + user.getRole().getName()
                    )
            );
        }

        return new AuthUserDetails(
                user.getUserName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
