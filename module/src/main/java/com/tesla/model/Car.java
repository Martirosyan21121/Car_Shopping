package com.tesla.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    @JoinColumn(name = "date_of_creation")
    private Date dateOfCreation;
    private String color;
    @JoinColumn(name = "power_reserve")
    private int powerReserve;
    @JoinColumn(name = "up_to_sixty")
    private double upToSixty;
    private int price;
    private int weight;
    @Enumerated(value = EnumType.STRING)
    private Autopilot autopilot;
    @JoinColumn(name = "max_speed")
    private int maxSpeed;
    @JoinColumn(name = "image_one")
    private String imageOne;
    @JoinColumn(name = "image_two")
    private String imageTwo;
    @JoinColumn(name = "image_three")
    private String imageThree;
    @JoinColumn(name = "image_four")
    private String imageFour;
    @JoinColumn(name = "image_five")
    private String imageFive;
    @JoinColumn(name = "image_six")
    private String imageSix;
    @JoinColumn(name = "inventory_type")
    @Enumerated(value = EnumType.STRING)
    private InventoryType inventoryType;
    @JoinColumn(name = "car_code")
    private String carCode;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && powerReserve == car.powerReserve && Double.compare(car.upToSixty, upToSixty) == 0 && price == car.price && weight == car.weight && maxSpeed == car.maxSpeed && Objects.equals(model, car.model) && Objects.equals(dateOfCreation, car.dateOfCreation) && Objects.equals(color, car.color) && autopilot == car.autopilot && Objects.equals(imageOne, car.imageOne) && Objects.equals(imageTwo, car.imageTwo) && Objects.equals(imageThree, car.imageThree) && Objects.equals(imageFour, car.imageFour) && Objects.equals(imageFive, car.imageFive) && Objects.equals(imageSix, car.imageSix) && inventoryType == car.inventoryType && Objects.equals(carCode, car.carCode) && Objects.equals(userId, car.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, dateOfCreation, color, powerReserve, upToSixty, price, weight, autopilot, maxSpeed, imageOne, imageTwo, imageThree, imageFour, imageFive, imageSix, inventoryType, carCode, userId);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                ", color='" + color + '\'' +
                ", powerReserve=" + powerReserve +
                ", upToSixty=" + upToSixty +
                ", price=" + price +
                ", weight=" + weight +
                ", autopilot=" + autopilot +
                ", maxSpeed=" + maxSpeed +
                ", imageOne='" + imageOne + '\'' +
                ", imageTwo='" + imageTwo + '\'' +
                ", imageThree='" + imageThree + '\'' +
                ", imageFour='" + imageFour + '\'' +
                ", imageFive='" + imageFive + '\'' +
                ", imageSix='" + imageSix + '\'' +
                ", inventoryType=" + inventoryType +
                ", carCode='" + carCode + '\'' +
                ", userId=" + userId +
                '}';
    }


}
