package com.example.demo.Domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
public User findUserByEmailAndPassword(String email,String password);
}
