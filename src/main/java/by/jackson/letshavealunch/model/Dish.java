package by.jackson.letshavealunch.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "dishes_unique_name_idx")})
public class Dish extends NamedEntity {
    public Dish() {
    }

    public Dish(Integer id, String name) {
        super(id, name);
    }

    public Dish(String name) {
        super(null, name);
    }
}

