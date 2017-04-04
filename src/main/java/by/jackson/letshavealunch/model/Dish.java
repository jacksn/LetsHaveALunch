package by.jackson.letshavealunch.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Embeddable
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "dishes_unique_name_idx")})
public class Dish {

    @Id
    @NotBlank
    @Column(name = "name", nullable = false)
    @SafeHtml
    private String name;

    @NotNull
    @Column
    private BigDecimal price;

    public Dish() {
    }

    public Dish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        return price != null ? price.equals(dish.price) : dish.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dish {" +
                name +
                " - " +
                price +
                '}';
    }
}

