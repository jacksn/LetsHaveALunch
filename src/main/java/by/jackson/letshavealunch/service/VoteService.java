package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.to.VoteTo;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Set;

public interface VoteService {

    Vote getByDateAndUserId(LocalDate date, int userId);

    Set<VoteTo> getByDate(LocalDate date, int userId);

    Vote save(Integer restaurantId, int userId);

    void delete(LocalDate date, int userId) throws NotFoundException;
}
