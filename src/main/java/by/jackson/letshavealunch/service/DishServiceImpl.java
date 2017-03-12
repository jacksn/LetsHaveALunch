package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.repository.DishRepository;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {
    private static final Sort SORT_NAME = new Sort("date,name");

    @Autowired
    private DishRepository repository;

//    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public Dish save(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

//    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.findOne(id), id);
    }

//    @Cacheable("dishes")
    @Override
    public List<Dish> getAll() {
        return repository.findAll(SORT_NAME);
    }

//    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        repository.save(dish);
    }

//    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void evictCache() {
    }
}
