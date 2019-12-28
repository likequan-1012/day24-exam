package com.lkq.controller;

import com.lkq.pojo.Item;
import com.lkq.pojo.User;
import com.lkq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("login")
    public Object login(@RequestBody User user){
        return userService.findByName(user);
    }


    @PostMapping("zhuc")
    public Object zhuc(@RequestBody User user){
        return userService.zhuc(user);
    }


    @PostMapping("list")
    public Object getList(@RequestBody Item item){
        return userService.selectList(item);
    }


    @GetMapping("car")
    public Object car(@RequestParam(value = "uid")Integer uid){

        return userService.selectById(uid);
    }

    @GetMapping("del")
    public Object del(@RequestParam(value = "id")Integer id,@RequestParam(value = "uid")Integer uid){

        return userService.delByCar(id,uid);
    }



}
