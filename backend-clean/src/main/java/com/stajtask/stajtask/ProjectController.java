package com.stajtask.stajtask;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
public class ProjectController {
    private final ProjectService projectService;









    @Autowired
    public ProjectController(ProjectService projectService){
        this.projectService=projectService;
    }






   @PostMapping("/{projectId}/assign/{employeeId}")
    public ResponseEntity<String> assignEmployeeToProject(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {

        Project updatedProject=projectService.assignEmployeeToProject(projectId, employeeId);
        return ResponseEntity.ok("Employee assigned to project successfully.");
   }














   /* @GetMapping("/{projectId}/employees")
    public ResponseEntity<Set<Employee>> getEmployeesByProject(@PathVariable Long projectId) {
        System.out.println("Project ID: " + projectId);
        Set<Employee> employees = projectService.getEmployeesByProject(projectId);
        System.out.println("Employees: " + employees);
        return ResponseEntity.ok(employees);
    }*/


    @GetMapping("/my-projects/{employeeId}")
    public List<Project> getProjectsByEmployee(@PathVariable Long employeeId) {
        return projectService.getProjectsByEmployee(employeeId);
    }


    @DeleteMapping("/{projectId}/remove/{employeeId}")
    public ResponseEntity<String> removeEmployeeFromProject(
            @PathVariable Long projectId,
            @PathVariable Long employeeId) {

        Project project = projectService.removeEmployeeFromProject(projectId, employeeId);
        return ResponseEntity.ok("Employee removed from project.");
    }





    @GetMapping
    public List<Project> getAll(){ //**
        return projectService.getAllProjects();
    }


    @GetMapping("/{id}") //**
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public Project create(@RequestBody Project project){ //**
        return projectService.addProject(project);
    }

    @PutMapping("/{id}")
    public Project update(@PathVariable Long id, @RequestBody Project project){
        project.setId(id);
        return projectService.updateProject(project);
    }







    @DeleteMapping("/{id}") //**
    public void delete(@PathVariable Long id){
        projectService.deleteProject(id);
    }

    @GetMapping("/byName")
        public List<Project> getByName(@RequestParam String name){
            return projectService.getProjectsByName(name);
        }


    @GetMapping("/byStatus")
    public List<Project> getByStatus(@RequestParam ProjectStatus status) {
        return projectService.getProjectsByStatus(status);
    }







}

//parametreler:
//@RequestParam: URL'de anahtar-değer çifti ile veri gönderiyorsan.
//@PathVariable:  URL’nin path (yol) kısmından veri alır URL’nin parçası olarak gönderilen veri alınacaksa (özellikle id gibi tanımlayıcılar için)
//@RequestBody: Kullanıcıdan komple bir nesne (JSON olarak) alacaksan