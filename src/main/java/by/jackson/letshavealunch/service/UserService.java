package by.jackson.letshavealunch.service;


import by.jackson.letshavealunch.model.User;
import by.jackson.letshavealunch.to.UserTo;
import by.jackson.letshavealunch.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(UserTo user);

    List<User> getAll();

    void update(User user);

    void evictCache();

    void enable(int id, boolean enable);
}
