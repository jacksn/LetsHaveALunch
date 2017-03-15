package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.MenuItem;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemService {
    MenuItem get(int id) throws NotFoundException;

    List<MenuItem> getByDate(int restaurantId, LocalDate date);

    List<MenuItem> getAll(int restaurantId);

    MenuItem save(MenuItem MenuItem);

    MenuItem update(MenuItem MenuItem) throws NotFoundException;

    void delete(int id) throws NotFoundException;

}
