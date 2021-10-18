package org.ex.services;

import org.ex.models.User;
import org.ex.models.UserType;
import org.ex.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("TaskServiceImpl")
public class TaskServiceImpl implements TaskService{

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean registerNewUser(User user) {
        User queryUser = this.userDao.getUserByUserName(user.getUser_name());

        if(queryUser == null) {
            final String firstName = user.getFirst_name();
            final String lastName = user.getLast_name();
            final String userName = user.getUser_name();
            final String type = user.getUser_type().toString();
            final String email = user.getEmail();
            final String password = user.getUser_password();
            userDao.insertUser(firstName, lastName, userName, type, email, password);
            return true;
        }
        return false;
    }

    @Override
    public User getUserByUserName(String username) {
        return this.userDao.getUserByUserName(username);
    }

}
