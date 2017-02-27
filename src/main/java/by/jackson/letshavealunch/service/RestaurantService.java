package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    void update(Restaurant restaurant);

    void evictCache();
}
