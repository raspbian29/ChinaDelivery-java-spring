package com.chinabox.delivery.dao;

import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByfName(String fName);

    List<User> findBylName(String lName);

    List<User> findByPhoneNumber(String phoneNumber);

    List<User> findBycreatedDate(LocalDate createdDate);

    List<User> findByrole(UserType role);

    User findByRemoteAddress(String remoteAddress);
}
