package com.tesla.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @JoinColumn(name = "local_time")
    private LocalTime localTime;
    private String name;
    private String subject;
    private String text;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(email, message.email) && Objects.equals(localTime, message.localTime) && Objects.equals(name, message.name) && Objects.equals(subject, message.subject) && Objects.equals(text, message.text) && Objects.equals(userId, message.userId);    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, localTime, name, subject, text, userId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", localTime=" + localTime +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                '}';
    }

}
