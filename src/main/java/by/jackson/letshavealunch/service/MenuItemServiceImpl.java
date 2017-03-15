package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.MenuItem;
import by.jackson.letshavealunch.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository repository;

    @Override
    public MenuItem get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<MenuItem> getByDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getByDate(restaurantId, date);
    }

    @Override
    public List<MenuItem> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        Assert.notNull(menuItem, "MenuItem must not be null");
        return repository.save(menuItem);
    }

    @Override
    public MenuItem update(MenuItem menuItem) {
        Assert.notNull(menuItem, "MenuItem must not be null");
        return checkNotFoundWithId(repository.save(menuItem), menuItem.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }
}
