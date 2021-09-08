package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import server.db.UserServiceImpl;
import server.db.entities.User;

import java.util.List;

@Controller
public class DBController {
    private final UserServiceImpl userService;
    private List<User> users;

    public DBController(UserServiceImpl userService) {
        this.userService = userService;
        users = userService.readAll();
    }

    public boolean equalsLocation(User user){
        for (User userFromDB:users) {
            if(userFromDB.getLocation().equals(user.getLocation())){
                return true;
            }
        }
        return false;
    }
}
