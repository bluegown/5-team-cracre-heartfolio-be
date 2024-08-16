package com.cracre.heartfoilo.domain.user.repository;

import com.cracre.heartfoilo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(long userId);
}
