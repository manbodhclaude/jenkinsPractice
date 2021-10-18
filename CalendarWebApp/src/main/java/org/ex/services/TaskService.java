package org.ex.services;

import org.ex.models.User;
import org.springframework.context.annotation.Bean;

public interface TaskService {

    boolean registerNewUser(User user);

    User getUserByUserName(String username);
}
