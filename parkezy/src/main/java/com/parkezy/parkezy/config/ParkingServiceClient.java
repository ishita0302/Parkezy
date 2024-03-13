package com.parkezy.parkezy.config;

import com.parkezy.parkezy.pojo.Parking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ParkingService", url = "http://localhost:8082") // "parking-service" is the name of the microservice
public interface ParkingServiceClient {

    @GetMapping("/parks/space/{id}")
    ResponseEntity<?> getSpace(@PathVariable long id);

    @GetMapping("/parks/allspace")
    ResponseEntity<List<Parking>> getAllSpaces();

    @PostMapping("/parks/add")
    Parking addSpace(@RequestBody Parking parking);

    @PutMapping("/parks/update_space")
    ResponseEntity<?> updateSpace(@RequestParam long id, @RequestBody Parking updatedUser);

    @DeleteMapping("/parks/delete/{id}")
    ResponseEntity<String> deleteSpace(@PathVariable long id);
}
