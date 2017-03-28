package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.model.Dish;
import by.jackson.letshavealunch.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNew;
import static by.jackson.letshavealunch.web.RestApiVersion.API_VERSION_STRING;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = API_VERSION_STRING + "/dishes";

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete dish with id {}", id);
        dishService.delete(id);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish dish) {
        LOG.info("update dish {}", dish);
        dishService.update(dish);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish) {
        checkNew(dish);
        LOG.info("create dish {} ", dish);
        Dish created = dishService.save(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
