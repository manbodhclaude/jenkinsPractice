package org.ex.repositories;

import org.ex.models.User;
import org.ex.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query(value = "select * from t_user where user_name = :username", nativeQuery = true)
    User getUserByUserName(@Param("username") String userName);

    @Modifying
    @Query(value = "insert into t_user (first_name, last_name, user_name, user_type, email, user_password)\n" +
            "values (:firstName, :lastName, :userName, :userType, :email, :password)", nativeQuery = true)
    void insertUser(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("userName") String userName, @Param("userType") String type, @Param("email") String email, @Param("password") String password);
}
