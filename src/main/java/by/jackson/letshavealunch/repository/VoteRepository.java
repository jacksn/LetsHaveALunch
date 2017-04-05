package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    int deleteByDateAndUserId(LocalDate date, int userId);

    @Override
    @Transactional
    Vote save(Vote vote);

    List<Vote> getByDate(LocalDate date);

    Vote getByDateAndUserId(LocalDate date, int userId);
}
