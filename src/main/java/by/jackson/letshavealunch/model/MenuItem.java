package by.jackson.letshavealunch.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu_items", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurant_id", "dish_id", "price"}, name = "menu_items_unique_idx")})
public class MenuItem extends BaseEntity {

    @NotNull
    @Column(name = "date", nullable = false, columnDefinition = "date default now()")
    private LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dish dish;

    @NotNull
    @Column(name = "price", nullable = false, columnDefinition = "real default 0")
    @Min(0)
    private float price;

    public MenuItem() {
    }

    public MenuItem(Restaurant restaurant, Dish dish, float price) {
        this(null, restaurant, dish, price, LocalDate.now());
    }

    public MenuItem(Restaurant restaurant, Dish dish, float price, LocalDate date) {
        this(null, restaurant, dish, price, date);
    }

    public MenuItem(Integer id, Restaurant restaurant, Dish dish, float price, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.dish = dish;
        this.price = price;
        this.date = date;
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MenuItem menuItem = (MenuItem) o;

        if (Float.compare(menuItem.price, price) != 0) return false;
        if (date != null ? !date.equals(menuItem.date) : menuItem.date != null) return false;
        if (restaurant != null ? !restaurant.equals(menuItem.restaurant) : menuItem.restaurant != null) return false;
        return dish != null ? dish.equals(menuItem.dish) : menuItem.dish == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        return result;
    }
}
