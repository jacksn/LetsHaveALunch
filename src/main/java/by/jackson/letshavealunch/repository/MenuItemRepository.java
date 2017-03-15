package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.MenuItem;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuItemRepository {

    MenuItem get(int id);

    // ORDERED by date
    List<MenuItem> getAll(int restaurantId);

    // ORDERED by date
    List<MenuItem> getByDate(int restaurantId, LocalDate date);

    MenuItem save(MenuItem MenuItem);

    boolean delete(int id);
}
