package com.tesla.reposervicesecurity.service;

import com.tesla.model.Car;
import com.tesla.reposervicesecurity.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepo carRepo;

    public void saveCar(Car car) {
        this.carRepo.save(car);
    }

    public CarService() {
    }
}
