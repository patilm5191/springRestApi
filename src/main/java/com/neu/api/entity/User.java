package com.neu.api.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByEmail", query = "SELECT u from User u where u.email=:pEmail"),
        @NamedQuery(name = "User.findAll", query = "SELECT u from User u ORDER BY u.firstName")
})
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String city;

    public User(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
