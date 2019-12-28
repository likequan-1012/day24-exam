package com.lkq.api;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "SERVER")
public interface UserApi {

    @RequestMapping("login")
    Object login(@RequestBody Object o);

    @RequestMapping("zhuc")
    Object zhuc(@RequestBody Object o);

    @PostMapping("list")
    Object list(@RequestBody Object o);

    @GetMapping("car")
    Object car(@RequestParam(value = "uid") Integer uid);

    @GetMapping("del")
    Object del(@RequestParam(value = "id")Integer id, @RequestParam(value = "uid")Integer uid);
}
