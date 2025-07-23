package com.stajtask.stajtask.security;

import com.stajtask.stajtask.Employee;
import com.stajtask.stajtask.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Frontend 3001 portu için izin ver
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          EmployeeRepository employeeRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> userData) {
        try {
            String username = userData.get("username");
            String password = userData.get("password");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String jwt = jwtUtil.generateToken(username);

            return ResponseEntity.ok(Map.of("token", jwt));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Yanlış kullanıcı adı veya şifre");
        }
    }

    // Basit register (isteğe bağlı)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee) {
        if(employeeRepository.findByUsername(employee.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Kullanıcı adı zaten mevcut");
        }

        // Şifreyi hashle
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        employeeRepository.save(employee);
        return ResponseEntity.ok("Kullanıcı oluşturuldu");
    }
}
