package com.demo.multisport.security;

import com.demo.multisport.dao.UserRepository;
import com.demo.multisport.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User can not be found" + username));

        return new SecurityUser(user);
    }
}
