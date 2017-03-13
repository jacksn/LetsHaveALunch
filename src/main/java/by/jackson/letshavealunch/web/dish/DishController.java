package by.jackson.letshavealunch.web.dish;

import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.service.DishService;
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
import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(DishController.REST_URL)
public class DishController {
    static final String REST_URL = "/dishes";

    private static final Logger LOG = LoggerFactory.getLogger(DishController.class);

    @Autowired
    private DishService dishService;

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        LOG.info("get dish with id {}", id);
        return dishService.get(id);
    }

    @GetMapping
    public List<Dish> getAll() {
        LOG.info("get all dishes");
        return dishService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete dish with id {}", id);
        dishService.delete(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish dish) {
        LOG.info("update dish {}", dish);
        dishService.update(dish);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish) {
        checkNew(dish);
        LOG.info("create dish {} ", dish);
        Dish created = dishService.save(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
