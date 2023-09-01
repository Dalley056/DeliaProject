package com.example.birthday.thymeleafSecurity;

import com.example.birthday.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DBUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DBUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DBUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        UserRole adminRole = userRepository.findByUsername(ADMIN);
        var userDetailsBuilder = DBUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
//                .authorities(user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()))
                .authority(new SimpleGrantedAuthority("ROLE_USER"));
        if (user.isAdmin()) {
            userDetailsBuilder.authority(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return userDetailsBuilder.build();
    }
}
