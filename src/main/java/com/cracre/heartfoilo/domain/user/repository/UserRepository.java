package com.cracre.heartfoilo.domain.user.repository;

import com.cracre.heartfoilo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
