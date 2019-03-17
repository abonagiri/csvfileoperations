package com.ashwin.csvfileoperations.repository;

import com.ashwin.csvfileoperations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {}
