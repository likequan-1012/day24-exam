package com.lkq.pojo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "t_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_image")
    private String image;

    @Column(name = "item_desc")
    private String desc;

    @Column(name = "item_price")
    private Double price;

    @Column(name = "item_brand")
    private String brand;

    @Column(name = "item_color")
    private String color;

    @Column(name = "item_Sales")
    private int sales;

    @Transient
    private Integer uid;

    @Transient
    private String status;

    @Transient
    private int pageNo;

    @Transient
    private int pageSize;

    @Transient
    private int num;

    @Transient
    private double subtotal;
}
