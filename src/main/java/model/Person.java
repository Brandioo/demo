package model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Data
@Entity
@Table(name = "people")
@NamedQueries(
    {
        @NamedQuery(
            name = "findAll",
            query = "SELECT p FROM Person p"
        )
    })
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "jobTitle", nullable = false)
    private String jobTitle;

    @Column(name = "yearBorn")
    @Min(value = 0)
    @Max(value = 9999)
    private int yearBorn;

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Person)) {
            return false;
        }

        Person person = (Person) o;

        return id == person.id &&
            yearBorn == person.yearBorn &&
            Objects.equals(fullName, person.fullName) &&
            Objects.equals(jobTitle, person.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, jobTitle, yearBorn);
    }
}
