package by.jackson.letshavealunch.service;

import by.jackson.letshavealunch.model.Vote;
import by.jackson.letshavealunch.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Collection;

import static by.jackson.letshavealunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public Collection<Vote> getByDate(LocalDate date, int userId) {
        Assert.notNull(date, "date must not be null");
        return repository.getByDate(date, userId);
    }

    @Override
    public Collection<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Vote save(Vote vote, int userId) {
        Assert.notNull(vote, "Vote must not be null");
        return repository.save(vote, userId);
    }

    @Override
    public Vote update(Vote vote, int userId) {
        Assert.notNull(vote, "Vote must not be null");
        return checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }
}
