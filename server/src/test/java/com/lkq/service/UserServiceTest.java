package com.lkq.service;

import com.lkq.dao.ItemDao;
import com.lkq.pojo.Car;
import com.lkq.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ItemDao itemDao;

    @Test
    public void insert(){
        Item item = itemDao.findById(1).get();

        Car car = new Car();
        car.setId(item.getId());
        car.setUid(1);
        car.setPrice(item.getPrice());
        car.setNum(0);

        mongoTemplate.save(car,"cat");


    }
    @Test
    public void getList(){
        Query query = new Query();
        Query user_id = query.addCriteria(Criteria.where("User_id").is(1));
        List<Car> cars = mongoTemplate.find(user_id, Car.class);
        ArrayList<Item> list = new ArrayList<>();
        cars.forEach(a->{
            Integer id = a.getId();
            Item item = itemDao.findById(id).get();
            list.add(item);
        });
        System.out.println(list);
    }
}
