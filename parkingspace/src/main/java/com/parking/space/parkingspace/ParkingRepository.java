package com.parking.space.parkingspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository <Parking,Long>{
    final List<Parking> parks = new ArrayList<>();
    @Autowired
    public default Optional<Parking> findByUsername(String username) {
        return parks.stream()
                .filter(parking -> parking.getUsername().equals(username))
                .findFirst();
    }

    /*@Autowired
    public default Optional<Parking> find(long loc) {
        return parks.stream()
                .filter(parking -> parking.getId() == loc)
                .findFirst();
    }*/

    /*@Autowired
    public default User save(User user) {
        users.add(user);
        return null;
    }*/

    // Additional methods as needed
}
