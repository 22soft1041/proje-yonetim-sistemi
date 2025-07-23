package com.stajtask.stajtask;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity                          //Bu sınıfın veritabanı tablosuyla eşleneceğini belirtir.
@Table(name = "projects")        //Bu sınıfın veritabanında hangi tabloya karşılık geldiğini belirtir.
@Data                            //getter setter tostring gibi faydalı metodları otomatik olarak getirir.
@NoArgsConstructor               //Parametresiz (boş) constructor üretir: Hibernate nesneleri oluştururken genellikle boş constructor'a ihtiyaç duyar.
@AllArgsConstructor              //Tüm alanları içeren constructor üretir.
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project {

    @ManyToMany
    @JoinTable(
            name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @JsonIgnore
    private Set<Employee> employees=new HashSet<>();
    //Bu yapılar veritabanında project_employee adında bir ara tablo oluşturur.




    @Id //Bu alanın veritabanı tablosunda primary key (birincil anahtar) olduğunu belirtir.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genaratedValue: ID’nin otomatik olarak veritabanı tarafından üretilmesini sağlar. strategy = GenerationType.IDENTITY → Otomatik artan AUTO_INCREMENT gibi çalışır
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING) //status alanı bir enum (sabit değerler kümesi) tipindedir.
    private ProjectStatus status;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
//ozetle:
//Bu sınıf, JPA ile projects adlı tabloya bağlanan bir veri modeli (Entity) tanımlar.
//PostgreSQL'de bu tablo şöyle olur:
//id (PK)	name	description  	status
//1	       "Proje A"	"Tanım A"	"ACTIVE"

//@Entity: Bu sınıfın JPA varlığı olduğunu, yani veritabanında tabloya karşılık geldiğini belirtir.

//@Table(name = "projects"): Bu sınıfın veritabanındaki adının projects olduğunu belirtir.

//@Data: Lombok anotasyonu, otomatik olarak getter, setter, toString, equals, hashCode vb. metodları üretir.

//@NoArgsConstructor: Parametresiz constructor üretir (Hibernate için gereklidir).

//@AllArgsConstructor: Tüm alanları içeren constructor üretir.

//@JsonIgnoreProperties(...): Jackson tarafından serileştirme/deserileştirme sırasında
// Hibernate ile ilgili ekstra alanları yok sayar. JSON dönüşümlerinde hata alınmaması için önemlidir.