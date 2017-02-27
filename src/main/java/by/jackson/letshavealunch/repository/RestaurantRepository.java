package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();
}
