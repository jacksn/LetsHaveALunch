package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MenuService {
    Menu get(int id) throws NotFoundException;

    Menu save(Menu menu);

    Menu update(Menu menu) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Menu> getAll();

    List<Menu> getByDate(LocalDate date);

    List<Menu> getByDateAndRestaurant(LocalDate date, int restaurantId);

    List<Menu> getByRestaurant(int restaurantId);
}
