package by.jackson.letshavealunch.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {

    @NotEmpty
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dishes", joinColumns = @JoinColumn(name = "restaurant_id"))
    @BatchSize(size = 20)
    private Set<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(String name, Set<Dish> dishes) {
        this(null, name, dishes);
    }

    public Restaurant(Integer id, String name, Set<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant {" +
                "id=" + getId() +
                ", name=" + getName() +
                ", dishes=" + dishes +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Restaurant that = (Restaurant) o;

        return dishes != null ? dishes.equals(that.dishes) : that.dishes == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }
}
