package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.model.User;
import by.jackson.letshavealunch.service.UserService;
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

import static by.jackson.letshavealunch.util.ValidationUtil.checkIdConsistent;
import static by.jackson.letshavealunch.util.ValidationUtil.checkNew;
import static by.jackson.letshavealunch.web.RestApiVersion.API_VERSION_STRING;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    public static final String REST_URL = API_VERSION_STRING + "/users";

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll() {
        LOG.info("get all");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        checkNew(user);
        LOG.info("create " + user);
        User created = service.save(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user, @PathVariable("id") int id) {
        LOG.info("update " + user);
        checkIdConsistent(user, id);
        service.update(user);
    }

    @GetMapping(value = "/by")
    public User getByMail(@RequestParam("email") String email) {
        LOG.info("getByEmail " + email);
        return service.getByEmail(email);
    }
}
