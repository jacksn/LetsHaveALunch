package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.service.MenuService;
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
@RequestMapping(MenuController.REST_URL)
public class MenuController {
    static final String REST_URL = "/menus";

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping("/{id}")
    public Menu get(@PathVariable("id") int id) {
        LOG.info("get menu with id {}", id);
        return menuService.get(id);
    }

    @GetMapping
    public List<Menu> getFiltered(@RequestParam(value = "date", required = false) LocalDate date,
                                  @RequestParam(value = "restaurant", required = false) Integer restaurantId) {
        if (date != null && restaurantId != null) {
            LOG.info("get menus by date = " + date + " and for restaurant with id = " + restaurantId);
            return menuService.getByDateAndRestaurant(date, restaurantId);
        } else if (date != null ) {
            LOG.info("get menus by date = " + date);
            return menuService.getByDate(date);
        } else if (restaurantId != null) {
            LOG.info("get menus for restaurant with id = " + restaurantId);
            return menuService.getByRestaurant(restaurantId);
        } else {
            LOG.info("get all menus");
            return menuService.getAll();
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete menu with id {}", id);
        menuService.delete(id);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Menu menu) {
        LOG.info("update menu {}", menu);
        menuService.update(menu);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@Valid @RequestBody Menu menu) {
        checkNew(menu);
        LOG.info("create menu {} ", menu);
        Menu created = menuService.save(menu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
