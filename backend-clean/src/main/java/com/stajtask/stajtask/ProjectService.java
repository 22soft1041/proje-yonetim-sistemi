package com.stajtask.stajtask;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service//Genellikle iş mantığı (business logic) burada yazılır.
public class ProjectService {
    private final ProjectRepository projectRepository;
    //Spring Boot, bu sınıfı oluştururken ProjectRepository'yi otomatik olarak constructor ile enjekte eder.
    //Yani şu yapı kullanılmış: Constructor-based Dependency Injection
    //Böylece bu service sınıfı, veritabanıyla etkileşim kurmak için repository’ye ulaşabilir.

    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository,EmployeeRepository employeeRepository){
        this.projectRepository=projectRepository;
        this.employeeRepository=employeeRepository;
    }



    @Transactional
    public Project assignEmployeeToProject(Long projectId, Long employeeId){
        Project project=projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        project.getEmployees().add(employee);
        employeeRepository.save(employee);
        return projectRepository.save(project);
    }



    /*@Transactional
    public Set<Employee> getEmployeesByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        return project.getEmployees();
    }*/

    public List<Project> getProjectsByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Çalışan bulunamadı: " + employeeId));
        return projectRepository.findByEmployeesContaining(employee);
    }



    @Transactional
    public Project removeEmployeeFromProject(Long projectId, Long employeeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

        project.getEmployees().remove(employee);

        employeeRepository.save(employee);
        return projectRepository.save(project);
    }



    public Project addProject(Project project){ //**
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){ //**
        return projectRepository.findAll();
    }


    public Project updateProject(Project project){
        return projectRepository.save(project);
    }




    public void deleteProject(Long id){ //**
        projectRepository.deleteById(id);
    }

    public List<Project> getProjectsByName(String name){
        return projectRepository.findByName(name);
    }

    public List<Project> getProjectsByStatus(ProjectStatus status){
        return projectRepository.findByStatus(status);
    }

    public Project getProjectById(Long id) { //**
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
    }






}

// Neden Service Katmanı Kullanılır?
//Controller doğrudan Repository yerine Service'e ulaşır.
//Böylece iş mantığı ayrıştırılır, kod daha temiz ve test edilebilir olur.
//Gerektiğinde ProjectService içine validasyon, loglama, hata yönetimi eklenebilir.
