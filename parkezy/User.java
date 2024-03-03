package com.parkezy.parkezy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_information")
    private String profileInformation;

    // Constructors, getters, setters

    // Default constructor
    public User() {
    }

    // Constructor with parameters
    public User(String username, String email, String password, String profileInformation) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profileInformation = profileInformation;
    }

    // Getters and setters

    // Omitting getters and setters for brevity

    // toString() method for easy debugging
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileInformation='" + profileInformation + '\'' +
                '}';
    }
}
