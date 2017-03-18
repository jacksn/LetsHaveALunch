package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Menu;
import by.jackson.letshavealunch.model.Restaurant;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Menu save(Menu menu);

    @Override
    Menu findOne(Integer id);

    @Override
    List<Menu> findAll(Sort sort);

    List<Menu> getByDate(LocalDate date);

    @Query("SELECT m FROM Menu m WHERE m.date = :date AND m.restaurant.id = :restaurant_id ORDER BY m.date DESC, m.restaurant.name ASC")
    List<Menu> getByDateAndRestaurant(@Param("date") LocalDate date, @Param("restaurant_id") int restaurant_id);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurant_id ORDER BY m.date DESC, m.restaurant.name ASC")
    List<Menu> getByRestaurant(@Param("restaurant_id") int restaurant_id);
}
