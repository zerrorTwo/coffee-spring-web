package com.nghia.coffee_spring_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nghia.coffee_spring_web.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
