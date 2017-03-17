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
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId ORDER BY v.date DESC")
    List<Vote> getAll(@Param("userId") int userId);

    //@SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Vote v WHERE v.user.id = :userId AND v.date = :date ORDER BY v.date DESC")
    List<Vote> getByDate(@Param("date") LocalDate date, @Param("userId") int userId);

    @Override
    Vote save(Vote item);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id = :id AND v.user.id = :userId")
    int delete(@Param("id") int id, @Param("userId") int userId);
}