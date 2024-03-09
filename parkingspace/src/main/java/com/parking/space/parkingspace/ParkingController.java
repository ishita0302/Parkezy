package com.parking.space.parkingspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.parking.space.parkingspace.customexception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parks")
public class ParkingController extends ResponseEntityExceptionHandler {
    private final ParkingService parkingService;
    private final Logger logger = LoggerFactory.getLogger(ParkingController.class);

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    //controls......//
    @GetMapping("/space/{id}")
    public ResponseEntity<?> getSpace(@PathVariable long id) {
        try {
            logger.debug("Attempting to retrieve space, ID: {}", id);
            Optional<Parking> userOptional = parkingService.getSpace(id);

            if (userOptional.isPresent()) {
                Parking user = userOptional.get();
                logger.info("Space retrieved successfully, ID: {}", id);
                return new ResponseEntity<>(user, HttpStatus.OK);

            } else {
                logger.warn("Space not found, ID: {}", id);
                return new ResponseEntity<>("Space not found", HttpStatus.NOT_FOUND);
            }

        } catch (RuntimeException e) {
            logger.error("Error retrieving space, ID: {}", id, e);
            throw new RuntimeException("Error retrieving space: " + e.getMessage());
            //return new ResponseEntity<>("Error retrieving user profile: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allspace")
    public ResponseEntity<?> getAllSpaces() {
        try {
            List<Parking> userOptional = parkingService.getAllSpaces();
            logger.info("Retrieved all space successfully");
            return new ResponseEntity<>(userOptional, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving all space", e);
            return new ResponseEntity<>("Error retrieving all spaces: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public Parking addSpace(@RequestBody Parking user) {
        try {
            Parking addedUser = parkingService.addSpace(user);
            logger.info("Space added successfully: {}", addedUser);
            return new ResponseEntity<>(addedUser, HttpStatus.CREATED).getBody();
        } catch (Exception e) {
            logger.error("Error adding space", e);
            ResponseEntity<String> stringResponseEntity = new ResponseEntity<>("Error adding space: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            //return stringResponseEntity;
        }
        return user;
    }

    @PutMapping("/update_space")
    public ResponseEntity<?> updateSpace(@RequestParam long id, @RequestBody Parking updatedUser) {
        try {
            // Log for debugging
            Optional<Parking> userOptional = parkingService.getSpace(id);
            if (userOptional.isPresent()){
                System.out.println("Updating space for user ID: " + id);

                Parking savedUser = parkingService.updateSpace(id, updatedUser);

                // Log for debugging
                logger.info("Space updated successfully: " + savedUser);
                return new ResponseEntity<>(savedUser, HttpStatus.OK);
            } else {
                logger.warn("Space not found for ID: " + id);
                return new ResponseEntity<>("Space not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log for debugging
            logger.error("Error updating space", e);

            return new ResponseEntity<>("Error updating space: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable long id) {
        try {
            Optional<Parking> userOptional = parkingService.getSpace(id);

            if (userOptional.isPresent()) {
                parkingService.deleteSpace(id);
                logger.info("Space deleted successfully, ID: {}", id);
                return new ResponseEntity<>("Space deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("Space not found, ID: {}", id);
                return new ResponseEntity<>("Space not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting space, ID: {}", id, e);
            return new ResponseEntity<>("Error deleting space: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}