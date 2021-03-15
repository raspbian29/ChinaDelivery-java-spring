package com.chinabox.delivery.dao;

import com.chinabox.delivery.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Long> {

}
