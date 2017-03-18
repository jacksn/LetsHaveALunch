package by.jackson.letshavealunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.FIELD)
public class MenuItem {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @NotNull
    @Column
    private Float price;

    public MenuItem() {
    }

    public MenuItem(Dish dish, Float price) {
        this.dish = dish;
        this.price = price;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (dish != null ? !dish.equals(menuItem.dish) : menuItem.dish != null) return false;
        return price != null ? price.equals(menuItem.price) : menuItem.price == null;
    }

    @Override
    public int hashCode() {
        int result = dish != null ? dish.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuItem {" +
                "dish=" + dish +
                ", price=" + price +
                '}';
    }
}
