package by.jackson.letshavealunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class MenuItem extends NamedEntity {

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

    public MenuItem() {
    }

    public MenuItem(String name, float price, LocalDate date) {
        this(null, name, price, date);
    }

    public MenuItem(Integer id, String name, float price, LocalDate date) {
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
