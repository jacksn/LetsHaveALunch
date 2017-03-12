package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.util.List;

public interface DishService {

    Dish save(Dish dish);

    void delete(int id) throws NotFoundException;

    Dish get(int id) throws NotFoundException;

    List<Dish> getAll();

    void update(Dish dish);

    void evictCache();
}
