package com.lkq.dao;

import com.lkq.pojo.Car;
import com.lkq.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarDao extends PagingAndSortingRepository<Car,Integer> {

}
