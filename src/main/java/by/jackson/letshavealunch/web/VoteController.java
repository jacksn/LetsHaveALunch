package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.AuthorizedUser;
import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static by.jackson.letshavealunch.util.ValidationUtil.checkIdConsistent;
import static by.jackson.letshavealunch.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    public static final String REST_URL = "/votes";

    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService service;

    @GetMapping("/{id}")
    public Vote get(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        LOG.info("get vote {} for User {}", id, userId);
        return service.get(id, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete vote {} for User {}", id, userId);
        service.delete(id, userId);
    }

    @GetMapping
    public List<Vote> getAll() {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        return service.getAll(userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Vote vote, @PathVariable("id") int id) {
        checkIdConsistent(vote, id);
        int userId = AuthorizedUser.id();
        LOG.info("update {} for User {}", vote, userId);
        service.update(vote, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@Valid @RequestBody Vote vote) {
        checkNew(vote);
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", vote, userId);
        Vote created = service.save(vote, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<Vote> getByDate(@RequestParam(value = "date", required = false) LocalDate date) {
        int userId = AuthorizedUser.id();
        LOG.info("getByDate {} for User {}", date, userId);
        return date != null ? service.getByDate(date, userId) : service.getAll(userId);
    }
}
