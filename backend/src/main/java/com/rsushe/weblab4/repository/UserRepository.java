package com.rsushe.weblab4.repository;

import com.rsushe.weblab4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
