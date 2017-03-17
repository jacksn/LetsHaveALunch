package by.jackson.letshavealunch.service;


import by.jackson.letshavealunch.model.User;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    User save(User user);

    void delete(int id) throws NotFoundException;

    void update(User user);

    void evictCache();
}
