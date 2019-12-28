package com.lkq.service;

import com.lkq.dao.ItemDao;
import com.lkq.dao.UserDao;
import com.lkq.pojo.Car;
import com.lkq.pojo.Item;
import com.lkq.pojo.User;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Object findByName(User user) {

        User userLogin = userDao.findByName(user.getName());

        HashMap<String, Object> map = new HashMap<>();

        if (user.getPassword().equals(userLogin.getPassword())&&user.getName().equals(userLogin.getName())){
            map.put("flag",true);
            map.put("user",userLogin);
            return map;
        }
        map.put("flag",false);
        map.put("msg","密码错误");

        return map;
    }

    @Override
    public Object zhuc(User user) {
        return userDao.saveAndFlush(user);
    }

    @Override
    public Object selectList(Item item) {


        StringBuffer sql = new StringBuffer(" select * from t_item where 1=1 ");

        StringBuffer count = new StringBuffer(" select count(*) from t_item where 1=1 ");

        if (item.getBrand()!=null){
            sql.append(" and item_brand = '"+item.getBrand()+"'");
            count.append(" and item_brand = '"+item.getBrand()+"'");

        }
        if (item.getColor()!=null){
            sql.append(" and item_color = '"+item.getColor()+"'");
            count.append(" and item_brand = '"+item.getBrand()+"'");
        }
        /*if (item.getStatus()!=null&&item.getStatus().equals("0")){
            sql.append(" ORDER BY  item_price desc  ");
        }*/
        if (item.getStatus()!=null&&item.getStatus().equals("sales")){
            sql.append(" ORDER BY  item_Sales desc  ");
        }
        if (item.getStatus()!=null&&item.getStatus().equals("price")){
            sql.append(" ORDER BY  item_price desc  ");
        }
        System.out.println(item.getBrand());
        List list = entityManager.createNativeQuery(sql.toString(), Item.class).setFirstResult((item.getPageNo()-1)*item.getPageSize()).setMaxResults(item.getPageSize()).getResultList();

        Object result = entityManager.createNativeQuery(count.toString()).getSingleResult();

        Integer counts = Integer.valueOf(result.toString());

        Integer pages = counts % item.getPageSize() == 0 ? counts / item.getPageSize() : counts / item.getPageSize() + 1;

        HashMap<String, Object> map = new HashMap<>();

        map.put("list",list);
        map.put("pages",pages);

        return map;
    }

    @Override
    public Object selectById(Integer uid) {
        System.out.println(uid);
        Query query = new Query();
        Query user_id = query.addCriteria(Criteria.where("User_id").is(uid));
        List<Car> cars = mongoTemplate.find(user_id, Car.class);
        ArrayList<Object> list = new ArrayList<>();
        cars.forEach(a->{
            Integer id = a.getId();
            Item item = itemDao.findById(id).get();
            Integer num = a.getNum();
            Double price = a.getPrice();
            item.setSubtotal(num*price);
            item.setNum(num);
            list.add(item);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",list);
        map.put("cars",cars);

        //删除user_id对应的集合
        /*mongoTemplate.remove(query,"cat");*/

        return map;
    }
    @Override
    public Object delByCar(Integer id, Integer uid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Item_id").is(id).and("User_id").is(uid));
        DeleteResult cat = mongoTemplate.remove(query, "cat");
        return cat;
    }
}
