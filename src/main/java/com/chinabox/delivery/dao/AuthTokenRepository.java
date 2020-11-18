package com.chinabox.delivery.dao;

import com.chinabox.delivery.model.AuthToken;
import com.chinabox.delivery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    AuthToken getByKey(String key);

    List<AuthToken> getByUser(User user);
}
