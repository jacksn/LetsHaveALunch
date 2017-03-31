package by.jackson.letshavealunch.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends NamedEntity {

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dishes", joinColumns = @JoinColumn(name = "restaurant_id"))
    @BatchSize(size = 20)
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this(null, name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
