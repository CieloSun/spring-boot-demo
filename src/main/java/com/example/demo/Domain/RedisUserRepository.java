package com.example.demo.Domain;

import org.springframework.data.repository.CrudRepository;

public interface RedisUserRepository extends CrudRepository<RedisUser,Long> {

}
