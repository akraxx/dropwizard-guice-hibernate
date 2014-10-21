package fr.mmarie.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Maximilien on 16/10/2014.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "person")
@NamedQueries(value = {
        @NamedQuery(
                name = "fr.mmarie.core.Person.findAll",
                query = "SELECT p FROM Person p"
        ),
        @NamedQuery(
                name = "fr.mmarie.core.Person.findById",
                query = "SELECT p FROM Person p WHERE p.id = :id"
        ),
        @NamedQuery(
                name = "fr.mmarie.core.Person.findByDepartmentId",
                query = "SELECT p FROM Person p where p.department.id = :departmentId"
        ),
})
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
