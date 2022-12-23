package com.tesla.reposervicesecurity.repo;

import com.tesla.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {


}
