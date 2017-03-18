package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {
    private static final Sort SORT_NAME = new Sort(new Sort.Order(Sort.Direction.DESC, "date"), new Sort.Order("restaurant.name"));

    @Autowired
    private MenuRepository repository;

    @Override
    public Menu get(int id) {
        return checkNotFoundWithId(repository.findOne(id), id);
    }

    @Override
    public Menu save(Menu menu) {
        Assert.notNull(menu, "Menu must not be null");
        Menu saved = repository.save(menu);
        repository.flush();
        return saved;
    }

    @Override
    public Menu update(Menu menu) {
        Assert.notNull(menu, "Menu must not be null");
        return checkNotFoundWithId(repository.save(menu), menu.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public List<Menu> getAll() {
        return repository.findAll(SORT_NAME);
    }

    @Override
    public List<Menu> getByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getByDate(date);
    }

    @Override
    public List<Menu> getByDateAndRestaurant(LocalDate date, int restaurantId) {
        return repository.getByDateAndRestaurant(date, restaurantId);
    }

    @Override
    public List<Menu> getByRestaurant(int restaurantId) {
        return repository.getByRestaurant(restaurantId);
    }
}
