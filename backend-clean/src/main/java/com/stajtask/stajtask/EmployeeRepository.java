package com.stajtask.stajtask;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findById(Long id);
    Optional<Employee> findByUsername(String username);

}
