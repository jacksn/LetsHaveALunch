package by.jackson.letshavealunch.repository;

import by.jackson.letshavealunch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    int deleteById(int id);

    @Override
    @Transactional
    User save(User user);

    User getByEmail(String email);
}
