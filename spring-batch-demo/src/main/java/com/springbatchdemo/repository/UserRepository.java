package com.springbatchdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbatchdemo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
