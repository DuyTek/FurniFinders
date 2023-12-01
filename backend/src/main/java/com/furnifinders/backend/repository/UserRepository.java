package com.furnifinders.backend.repository;


import com.furnifinders.backend.Entity.Role;
import com.furnifinders.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {




    Optional<User> findByEmail(String email);

    User findByRole(Role role);






}
