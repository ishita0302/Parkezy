package com.parking.space.parkingspace;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "location")
    private String location;

    @Column(name = "available")
    private String available;

    @Column(name = "price")
    private String price;

    // Constructors, getters, setters

    // Default constructor
    public Parking() {
    }

    // Constructor with parameters
    public Parking(String username, String location, String available, String price) {
        this.username = username;
        this.location = location;
        this.available = available;
        this.price = price;
    }

    // Getters and setters

    // Omitting getters and setters for brevity

    // toString() method for easy debugging
    @Override
    public String toString() {
        return "Parking{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", location='" + location + '\'' +
                ", available='" + available + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


}