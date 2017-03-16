package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.MenuItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId ORDER BY m.date DESC, m.dish.name ASC")
    List<MenuItem> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT m from MenuItem m WHERE m.restaurant.id = :restaurantId AND m.date = :date ORDER BY m.dish.name ASC")
    List<MenuItem> getByDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Override
    MenuItem save(MenuItem item);

    @Modifying
    @Transactional
    @Query("DELETE FROM MenuItem m WHERE m.id = :id")
    int delete(@Param("id") int id);
}
