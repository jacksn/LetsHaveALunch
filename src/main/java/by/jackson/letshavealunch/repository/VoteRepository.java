package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    // null if Vote does not belong to userId
    Vote get(int id, int userId);

    // ORDERED by date
    List<Vote> getAll(int userId);

    // ORDERED by date
    List<Vote> getByDate(LocalDate date, int userId);

    // null if updated Vote does not belong to userId
    Vote save(Vote Vote, int userId);

    // false if Vote does not belong to userId
    boolean delete(int id, int userId);

//    default Vote getWithUser(int id, int userId) {
//        throw new UnsupportedOperationException();
//    }
}
