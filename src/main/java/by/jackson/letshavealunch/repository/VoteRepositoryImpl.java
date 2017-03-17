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

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return voteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        Vote Vote = voteRepository.findOne(id);
        return Vote != null && Vote.getUser().getId() == userId ? Vote : null;
    }

    @Override
    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }

    @Override
    public List<Vote> getByDate(LocalDate date, int userId) {
        return voteRepository.getByDate(date, userId);
    }
}
