package com.lkq.pojo;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "cat")
@Data
public class Car {

    @Field("Item_id")
    private Integer id;

    @Field("Item_number")
    private Integer num;

    @Field("Item_price")
    private Double price;

    @Field("User_id")
    private Integer uid;

}
