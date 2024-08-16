package com.heartfoilo.demo.domain.donation.repository;

import com.heartfoilo.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<User, Long> {

}
