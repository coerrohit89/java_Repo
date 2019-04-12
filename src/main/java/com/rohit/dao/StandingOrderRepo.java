package com.rohit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rohit.model.StandingOrder;

@Repository
public interface StandingOrderRepo extends CrudRepository<StandingOrder, Long> {

}
