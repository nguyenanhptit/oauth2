package com.oauth2.repository;

import com.oauth2.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;
@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, String> {
    /**
     * create one user
     */
    void save(User user);

    /**
     * Fine user by id user
     */
    User findById(String id);

    /**
     * Check exist user by id
     */
    boolean existsById(String id);

    User findUserByTokenIdToken(String tokenId);

    List<User> findAll();
}
