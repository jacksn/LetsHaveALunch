package by.jackson.letshavealunch.web.restaurant;

import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.service.DishService;
import by.jackson.letshavealunch.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(RestaurantController.REST_URL)
public class RestaurantController {
    static final String REST_URL = "/restaurants";

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        LOG.info("get restaurant with id {}", id);
        return restaurantService.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        LOG.info("get all restaurants");
        return restaurantService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete restaurant with id {}", id);
        restaurantService.delete(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant restaurant) {
        LOG.info("update restaurant {}", restaurant);
        restaurantService.update(restaurant);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        LOG.info("create restaurant {} ", restaurant);
        Restaurant created = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}/dishes?date={date}")
    public List<Dish> getDishes(@PathVariable int id, @RequestParam(value = "date", required = false) LocalDate date) {
//        if (date == null) {
//            dishService.
//        }
//        return dishService.get;
        return null;
    }
}
