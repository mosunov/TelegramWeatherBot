package server.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.db.entities.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void create(User user){
        usersRepository.save(user);
    }

    @Override
    public List<User> readAll() {
        return usersRepository.findAll();
    }

    @Override
    public boolean contains(User user) {
        return true;
    }
}
