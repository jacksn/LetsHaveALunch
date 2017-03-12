package by.jackson.letshavealunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Menu extends NamedEntity {

    @NotNull
    @Column(name = "price", nullable = false, columnDefinition = "real default 0")
    private float price;

    @NotNull
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Dish> dishes;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(String name, float price, LocalDate date) {
        this(null, name, price, date);
    }

    public Menu(Integer id, String name, float price, LocalDate date) {
        super(id, name);
        this.price = price;
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
