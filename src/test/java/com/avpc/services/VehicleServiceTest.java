package com.avpc.services;
import com.avpc.model.Vehicle;
import com.avpc.model.dao.VehicleDAO;
import com.avpc.restfulcontrollers.dto.VehicleDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleServiceTest {

    @MockBean
    VehicleDAO vehicleDAO;

    private Vehicle vehicle;

    private VehicleDTO vehicleDTO;

    @Autowired
    private VehicleService sut;

    @Before
    public void setUp(){

        List<Vehicle> listVehicle = new ArrayList<>();

        vehicle = new Vehicle();
        vehicle.setPhotoURL("/test");
        vehicle.setBrand("BMW");
        vehicle.setModel("Z4");
        vehicle.setCredential("AVPC-4");
        vehicle.setRegistrationNumber("112");

        listVehicle.add(vehicle);

        vehicleDTO = new VehicleDTO();
        vehicleDTO.setCredential("AVPC-4");
        vehicleDTO.setModel("Z4");
        vehicleDTO.setBrand("BMW");
        vehicleDTO.setRegistrationNumber("112");

        when(vehicleDAO.save(any(Vehicle.class))).thenReturn(vehicle);
        when(vehicleDAO.findOne(anyLong())).thenReturn(vehicle);
        when(vehicleDAO.findAll()).thenReturn(listVehicle);
    }

    @Test
    public void testAddVehicle(){
        Vehicle response = sut.addVehicle(vehicleDTO);
        checkResponse(response);
    }

    @Test
    public void testGetVehicle(){
        Vehicle response = sut.getVehicle(1L);
        checkResponse(response);
    }

    @Test
    public void testGetAllVehicles(){
        List<Vehicle> response = sut.getAllVehicles();
        checkResponse(response);
    }

    @Test
    public void testUpdateVehicle(){
        Vehicle response = sut.updateVehicle(1L,vehicleDTO);
        checkResponse(response);
    }

    @Test
    public void deleteVehicle(){
        try {
            sut.deleteVehicle(1L);
        } catch (Exception e){
            Assert.fail();
        }
    }

    private void checkResponse(Vehicle response){
        Assert.assertTrue(response.getBrand().equals("BMW"));
        Assert.assertTrue(response.getCredential().equals("AVPC-4"));
        Assert.assertTrue(response.getModel().equals("Z4"));
        Assert.assertTrue(response.getPhotoURL().equals("/test"));
        Assert.assertTrue(response.getRegistrationNumber().equals("112"));
    }

    private void checkResponse(List<Vehicle> response){
        Assert.assertTrue(response.get(0).getBrand().equals("BMW"));
        Assert.assertTrue(response.get(0).getCredential().equals("AVPC-4"));
        Assert.assertTrue(response.get(0).getModel().equals("Z4"));
        Assert.assertTrue(response.get(0).getPhotoURL().equals("/test"));
        Assert.assertTrue(response.get(0).getRegistrationNumber().equals("112"));
    }

}
