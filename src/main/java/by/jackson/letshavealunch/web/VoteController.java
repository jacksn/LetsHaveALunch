package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.AuthorizedUser;
import by.jackson.letshavealunch.service.VoteService;
import by.jackson.letshavealunch.to.VoteTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

import static by.jackson.letshavealunch.web.RestApiVersion.API_VERSION_STRING;

@RestController
@RequestMapping(value = VoteController.REST_URL)
public class VoteController {
    public static final String REST_URL = API_VERSION_STRING + "/votes";

    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<VoteTo> getVotes(@RequestParam(value = "date", required = false) LocalDate date) {
        int userId = AuthorizedUser.id();
        if (date == null) {
            date = LocalDate.now();
        }
        LOG.info("get votes by date = {} for User = {}", date, userId);
        return service.getByDate(date, userId);
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "date", required = false) LocalDate date) {
        int userId = AuthorizedUser.id();
        if (date == null) {
            date = LocalDate.now();
        }
        LOG.info("delete vote by date = {} for User {}", date, userId);
        service.delete(date, userId);
    }

    @PostMapping("{restaurantId}")
    public void vote(@PathVariable Integer restaurantId) {
        int userId = AuthorizedUser.id();
        service.save(restaurantId, userId);
        LOG.info("create vote for restaurant with id = {} for User {}", restaurantId, AuthorizedUser.get().getUser());
    }
}
