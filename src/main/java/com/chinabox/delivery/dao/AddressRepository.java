package com.chinabox.delivery.dao;

import com.chinabox.delivery.model.User;
import com.chinabox.delivery.model.UserAddress;
import org.apache.tomcat.jni.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUser(User user);
}