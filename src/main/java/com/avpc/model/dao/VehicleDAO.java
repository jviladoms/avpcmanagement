package com.avpc.model.dao;

import com.avpc.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
   public interface VehicleDAO extends CrudRepository<Vehicle, Long> {
}