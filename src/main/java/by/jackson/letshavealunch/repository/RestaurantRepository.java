package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    int deleteById(int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);
}
