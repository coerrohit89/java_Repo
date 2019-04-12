package com.rohit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rohit.model.Scheduler;

@Repository
public interface SchedulerRepo extends CrudRepository<Scheduler, Long> {

}
