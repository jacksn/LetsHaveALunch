package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.repository.RestaurantRepository;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void evictCache() {
    }
}
