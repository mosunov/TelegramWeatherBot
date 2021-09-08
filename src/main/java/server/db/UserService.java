package server.db;

import server.db.entities.User;

import java.util.List;

public interface UserService {
    void create(User user);
    List<User> readAll();
    boolean contains(User user);
}
