package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    List<Vote> getByDate(LocalDate date);

    Vote getByDateForUser(LocalDate date, int userId);

    Vote save(Vote Vote, int userId);

    boolean delete(LocalDate date, int userId);
}
