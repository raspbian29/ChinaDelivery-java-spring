package com.chinabox.delivery.dao;

import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRequestRepository extends JpaRepository<PackageRequest, Long> {
     List<PackageRequest> findByUser(User user);

}
