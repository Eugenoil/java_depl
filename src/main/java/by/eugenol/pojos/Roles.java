package by.eugenol.pojos;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


/**
 * Entity Roles, has an id, name, many-to-many relationship.
 * Overridden equals method to make Roles unique.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Roles roles = (Roles) obj;
        if (!Objects.equals(this.name, roles.name)) {
            return false;
        }

        return Objects.equals(this.name, roles.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
