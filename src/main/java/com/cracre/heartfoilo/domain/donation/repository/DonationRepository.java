package com.cracre.heartfoilo.domain.donation.repository;

import com.cracre.heartfoilo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<User, Long> {

}
