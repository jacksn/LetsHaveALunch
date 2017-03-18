package by.jackson.letshavealunch.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurant_id"}, name = "menus_unique_idx")})
public class Menu extends BaseEntity {

    @NotNull
    @Column(name = "date", nullable = false, columnDefinition = "date default now()")
    private LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "menu_items", joinColumns = @JoinColumn(name = "menu_id"))
    @BatchSize(size = 20)
    private List<MenuItem> menuItems;

    public Menu() {
    }

    public Menu(LocalDate date, Restaurant restaurant) {
        this(null, date, restaurant, null);
    }

    public Menu(LocalDate date, Restaurant restaurant, List<MenuItem> menuItems) {
        this(null, date, restaurant, menuItems);
    }

    public Menu(Integer id, LocalDate date, Restaurant restaurant, List<MenuItem> menuItems) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
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

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Menu menu = (Menu) o;

        if (date != null ? !date.equals(menu.date) : menu.date != null) return false;
        if (restaurant != null ? !restaurant.equals(menu.restaurant) : menu.restaurant != null) return false;
        return menuItems != null ? menuItems.equals(menu.menuItems) : menu.menuItems == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (menuItems != null ? menuItems.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu {" +
                "id = " + getId() +
                ", date = " + date +
                ", restaurant = " + restaurant.name +
                ", menuItems = " + menuItems +
                '}';
    }
}