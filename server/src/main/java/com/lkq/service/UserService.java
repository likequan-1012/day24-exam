package com.lkq.service;

import com.lkq.pojo.Item;
import com.lkq.pojo.User;


public interface UserService {

    Object findByName(User user);

    Object zhuc(User user);

    Object selectList(Item item);

    Object selectById(Integer uid);

    Object delByCar(Integer id, Integer uid);
}
