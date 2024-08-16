package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.user.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long userId);
    //User findOrCreateUser(OAuth2User oauthUser);
}
