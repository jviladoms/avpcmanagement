package com.avpc.model.dao;

import com.avpc.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Jordi on 31/10/2016.
 */
@Transactional
   public interface VehicleDAO extends CrudRepository<Vehicle, Long> {
}