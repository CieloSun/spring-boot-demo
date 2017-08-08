package com.example.demo.Domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<User,Long> {

}
