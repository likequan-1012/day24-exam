package com.lkq.Controller;

import com.lkq.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userApi")
public class UserController {

    @Autowired
    private UserApi userApi;

    @PostMapping("loginApi")
    public Object login(@RequestBody Object o){
        return userApi.login(o);
    }

    @PostMapping("zhucApi")
    public Object zhuc(@RequestBody Object o){
        return userApi.zhuc(o);
    }


    @PostMapping("listApi")
    public Object list(@RequestBody Object o){
        return userApi.list(o);
    }

    @GetMapping("carApi")
    public Object car(@RequestParam(value = "uid")Integer uid){
        return userApi.car(uid);
    }

    @GetMapping("delApi")
    public Object del(@RequestParam(value = "id")Integer id,@RequestParam(value = "uid")Integer uid){
        return userApi.del(id,uid);
    }


}
