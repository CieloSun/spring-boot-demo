package com.example.demo.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByName(String name);
    Student findByNameAndAge(String name,Integer age);
    @Query("select student from Student student where name = :name")
    Student findUser(@Param("name")String name);
}