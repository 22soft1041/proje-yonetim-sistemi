package com.stajtask.stajtask;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByName(String name);
    Optional<Project> findById(Long id);
    List<Project> findByStatus(ProjectStatus status);
    List<Project> findByEmployeesContaining(Employee employee);





}
//Repository arayüzü, yani veritabanı işlemlerini (CRUD) yapmak için kullanılır.
//create read update delete

//JpaRepository, Spring Data JPA'nın sağladığı bir arayüzdür.
//Veritabanı işlemlerini (CRUD - Create, Read, Update, Delete) kolayca yapmanı sağlar.
//Elle SQL yazmadan veri çekebilir, kaydedebilir, silebilirsin.

//JpaRepository<Project,Long> --> project: hangi entity ile çalışacağını belirtir. Long: entity nin id türü

// Ne Sağlar?
//Sen bu arayüzü yazınca, Spring Boot sana hazır metotlar sunar, örneğin:
//projectRepository.findAll(); // tüm projeleri getirir
//projectRepository.findById(1L); // id'si 1 olan projeyi getirir
//projectRepository.save(project); // proje ekler veya günceller
//projectRepository.deleteById(1L); // projeyi siler
//SQL veya JPQL yazmana gerek kalmaz.
//Spring otomatik olarak bu metotların arkasında gereken sorguları çalıştırır.

//Ek Özellik: Kendi metotlarını da ekleyebilirsin
//Spring, method adına göre otomatik SQL üretir:
//List<Project> findByName(String name); // name’e göre proje getirir
//List<Project> findByStatus(ProjectStatus status); // Enum’a göre getirir
//Bunları arayüze eklemen yeterlidir. Gerisini Spring halleder.