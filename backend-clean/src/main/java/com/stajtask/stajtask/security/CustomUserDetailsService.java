package com.stajtask.stajtask.security;


import com.stajtask.stajtask.Employee;
import com.stajtask.stajtask.EmployeeRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(user.getUsername(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
    }
}

