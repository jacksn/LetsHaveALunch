package by.jackson.letshavealunch.model;

import javax.persistence.Entity;

@Entity
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

