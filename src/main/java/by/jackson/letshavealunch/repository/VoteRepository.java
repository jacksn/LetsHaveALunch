package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> getByDate(LocalDate date);

    @Query("SELECT v from Vote v WHERE v.user.id = :userId AND v.date = :date")
    Vote getByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.date = :date AND v.user.id = :userId")
    int delete(@Param("date") LocalDate date, @Param("userId") int userId);
}
