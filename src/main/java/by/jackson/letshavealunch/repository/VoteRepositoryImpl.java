package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository voteRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        return voteRepository.save(vote);
    }

    @Override
    public boolean delete(LocalDate date, int userId) {
        return voteRepository.delete(date, userId) != 0;
    }

    @Override
    public List<Vote> getByDate(LocalDate date) {
        return voteRepository.getByDate(date);
    }

    @Override
    public Vote getByDateForUser(LocalDate date, int userId) {
        return voteRepository.getByDateForUser(date, userId);
    }
}
