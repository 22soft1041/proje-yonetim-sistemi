package com.stajtask.stajtask;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){ //**
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){ //**
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}") //**
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id).orElseThrow();
    }

    @DeleteMapping("/{id}") //**
        public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
     public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }



    //Çalışanlara ait projeleri listelemek (Employee → Project)
   /* @GetMapping("/{employeeId}/projects")
    public List<Project> getProjectsForEmployee(@PathVariable Long employeeId){
        Employee employee = employeeService.getEmployeeById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return new ArrayList<>(employee.getProjects());
    }*/

}
