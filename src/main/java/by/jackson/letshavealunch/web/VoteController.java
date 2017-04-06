package by.jackson.letshavealunch.web;

import by.jackson.letshavealunch.AuthorizedUser;
import by.jackson.letshavealunch.model.Role;
import by.jackson.letshavealunch.service.VoteService;
import by.jackson.letshavealunch.to.VoteTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

import static by.jackson.letshavealunch.web.RestApiVersion.API_VERSION_STRING;

@RestController
@RequestMapping(value = VoteController.REST_URL)
public class VoteController {
    public static final String REST_URL = API_VERSION_STRING + "/votes";

    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    private VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<VoteTo> getVotesByDate(@RequestParam(value = "date") LocalDate date) {
        int userId = AuthorizedUser.id();
        if (date == null || !AuthorizedUser.get().getUser().getRoles().contains(Role.ROLE_ADMIN)) {
            date = LocalDate.now();
        }
        LOG.info("get votes by date = {} for User = {}", date, userId);
        return service.getByDate(date, userId);
    }

    @PostMapping("{restaurantId}")
    public void vote(@PathVariable Integer restaurantId) {
        int userId = AuthorizedUser.id();
        LOG.info("create or update vote for restaurant with id = {} for User {}", restaurantId, userId);
        service.save(restaurantId, userId);
    }

    @DeleteMapping("{restaurantId}")
    public void deleteVote(@PathVariable Integer restaurantId) {
        int userId = AuthorizedUser.id();
        LOG.info("create or update vote for restaurant with id = {} for User {}", restaurantId, userId);
        service.delete(LocalDate.now(), userId);
    }
}
