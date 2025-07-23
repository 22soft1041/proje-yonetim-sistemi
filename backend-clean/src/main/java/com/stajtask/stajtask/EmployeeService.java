package com.stajtask.stajtask;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;  // ekledik

    public EmployeeService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee createEmployee(Employee employee){
        // Şifreyi encode et
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }


    public List<Employee> getAllEmployees(){ //**
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }



    public void deleteEmployee(Long id){ //**
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Employee employee){

        if (employee.getPassword() != null) {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }

        return employeeRepository.save(employee);
    }

}
