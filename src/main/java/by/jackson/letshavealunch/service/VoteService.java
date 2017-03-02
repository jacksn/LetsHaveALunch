package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

public interface VoteService {
    Vote get(int id, int userId) throws NotFoundException;

    Collection<Vote> getByDate(LocalDate date, int userId);

    Collection<Vote> getAll(int userId);

    Vote save(Vote vote, int userId);

    Vote update(Vote vote, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

//    Vote getWithRestaurant(int id, int userId);
//
//    Vote getWithMenu(int id, int userId);
}
