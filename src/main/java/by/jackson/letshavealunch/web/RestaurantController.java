package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.service.MenuService;
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
import static by.jackson.letshavealunch.web.RestApiVersion.API_VERSION_STRING;

@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = API_VERSION_STRING + "/restaurants";

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

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

    @GetMapping("/{id}/menus")
    public List<Menu> getDishes(@PathVariable int id, @RequestParam(value = "date", required = false) LocalDate date) {
        if (date != null) {
            LOG.info("get menus by date = " + date + " and by restaurant with id = " + id);
            return menuService.getByDateAndRestaurant(date, id);
        } else {
            LOG.info("get menus for restaurant with id = " + id);
            return menuService.getByRestaurant(id);
        }
    }
}
