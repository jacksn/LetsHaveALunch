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
}

