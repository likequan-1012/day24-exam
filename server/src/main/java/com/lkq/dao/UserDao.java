package com.lkq.dao;

import com.lkq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByName(String name);
}
