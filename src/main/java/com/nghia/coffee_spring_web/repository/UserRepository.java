package com.nghia.coffee_spring_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghia.coffee_spring_web.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User save(User user);

    User findById(long id);

    void deleteById(long id);
}
