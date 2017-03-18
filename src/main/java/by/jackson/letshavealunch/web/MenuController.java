package by.jackson.letshavealunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MenuController.REST_URL)
public class MenuController {
    static final String REST_URL = "/menus";

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

//    @Autowired
//    private RestaurantService restaurantService;
//
//    @Autowired
//    private MenuService menuItemService;
//
//    @Autowired
//    private DishService dishService;
//
//    @GetMapping("/{id}")
//    public MenuTo get(@PathVariable("id") int id) {
//        LOG.info("get menu with id {}", id);
//        return menuService.get(id);
//    }
//
//    @GetMapping
//    public List<MenuTo> getAll() {
//        LOG.info("get all menus");
//        return menuService.getAll();
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable("id") int id) {
//        LOG.info("delete menu with id {}", id);
//        menuService.delete(id);
//    }
//
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void update(@Valid @RequestBody Menu menu) {
//        LOG.info("update menu {}", menu);
//        menuService.update(menu);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Menu> create(@Valid @RequestBody Menu menu) {
//        checkNew(menu);
//        LOG.info("create menu {} ", menu);
//        Menu created = menuService.save(menu);
//
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
//
//    @GetMapping("/{id}/dishes?date={date}")
//    public List<Dish> getDishes(@PathVariable int id, @RequestParam(value = "date", required = false) LocalDate date) {
////        if (date == null) {
////            dishService.
////        }
////        return dishService.get;
//        return null;
//    }
}
