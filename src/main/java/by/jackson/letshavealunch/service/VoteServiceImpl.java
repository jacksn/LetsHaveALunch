package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.AppConfig;
import by.jackson.letshavealunch.model.Restaurant;
import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.repository.RestaurantRepository;
import by.jackson.letshavealunch.repository.UserRepository;
import by.jackson.letshavealunch.repository.VoteRepository;
import by.jackson.letshavealunch.to.VoteTo;
import by.jackson.letshavealunch.util.exception.VotingEndedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNotFound;
import static by.jackson.letshavealunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppConfig appConfig;

    @Override
    public Vote getByDateAndUserId(LocalDate date, int userId) {
        Assert.notNull(date, "date must not be null");
        return voteRepository.getByDateAndUserId(date, userId);
    }

    @Override
    public Set<VoteTo> getByDate(LocalDate date, int userId) {
        Restaurant usersRestaurant = null;

        Map<Restaurant, Integer> voteCountForRestaurants = new HashMap<>();
        for (Vote vote : voteRepository.getByDate(date)) {
            voteCountForRestaurants.merge(vote.getRestaurant(), 1, Integer::sum);
            if (vote.getUser().getId() == userId) {
                usersRestaurant = vote.getRestaurant();
            }
        }

        Set<VoteTo> result = new HashSet<>();
        Restaurant finalUsersRestaurant = usersRestaurant;
        voteCountForRestaurants.forEach(
                (key, value) -> result.add(new VoteTo(key, value, finalUsersRestaurant != null && key.equals(finalUsersRestaurant))));
        return result;
    }

    @Override
    public Vote save(Integer restaurantId, int userId) {
        Assert.notNull(restaurantId, "restaurant id must not be null");
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        checkNotFoundWithId(restaurant, restaurantId);

        LocalDateTime dateTime = LocalDateTime.now();

        Vote vote = voteRepository.getByDateAndUserId(dateTime.toLocalDate(), userId);
        if (dateTime.toLocalTime().isAfter(appConfig.getVotingEndTime()) && vote != null) {
            throw new VotingEndedException("Voting ended at " + appConfig.getVotingEndTime());
        }
        if (vote == null) {
            vote = new Vote(userRepository.getOne(userId), restaurant);
        } else {
            vote.setRestaurant(restaurant);
        }

        return voteRepository.save(vote);
    }

    @Override
    public void delete(LocalDate date, int userId) {
        checkNotFound(voteRepository.deleteByDateAndUserId(date, userId) != 0, "vote on " + date);
    }
}
