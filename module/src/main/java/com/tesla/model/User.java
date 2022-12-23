package com.tesla.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @JoinColumn(name = "local_date")
    private LocalDate localDate;
    private String name;
    private String surname;
    private String password;

    private Integer token;
    @JoinColumn(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(localDate, user.localDate) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(password, user.password) && Objects.equals(token, user.token) && userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, localDate, name, surname, password, token, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", localDate=" + localDate +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", token=" + token +
                ", userType=" + userType +
                '}';
    }
}
