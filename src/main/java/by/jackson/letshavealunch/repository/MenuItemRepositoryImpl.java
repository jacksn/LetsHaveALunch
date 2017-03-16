package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuItemRepositoryImpl implements MenuItemRepository {

    @Autowired
    private CrudMenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public MenuItem save(MenuItem menuItem) {
        if (!menuItem.isNew() && get(menuItem.getId()) == null) {
            return null;
        }
        return menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return menuItemRepository.delete(id) != 0;
    }

    @Override
    public MenuItem get(int id) {
        return menuItemRepository.findOne(id);
    }

    @Override
    public List<MenuItem> getAll(int restaurantId) {
        return menuItemRepository.getAll(restaurantId);
    }

    @Override
    public List<MenuItem> getByDate(int restaurantId, LocalDate date) {
        return menuItemRepository.getByDate(restaurantId, date);
    }
}
