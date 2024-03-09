package com.parkezy.parkezy;
import com.parkezy.parkezy.config.ParkingServiceClient;
import com.parkezy.parkezy.pojo.Parking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.parkezy.parkezy.customexception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController extends ResponseEntityExceptionHandler {
   // private ParkingService parkingService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*@PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User newUser) {
        userService.registerUser(newUser);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    } 

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> userOptional = userService.loginUser(username, password);
        return userOptional.map(user -> new ResponseEntity<>("Login successful", HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED));
    }*/

    /*@GetMapping("/data")
    public ResponseEntity<String> getDataFromParkingService() {
        String data = parkingService.getData();
        return ResponseEntity.ok("Data from Microservice 2: " + data);
    }*/
    @GetMapping("/allspaces")
    public ResponseEntity<?> getAllSpaces(){
        return userService.getAllSpaces();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable long id) {
        try {
            logger.debug("Attempting to retrieve user profile, ID: {}", id);
            Optional<User> userOptional = userService.getUserProfile(id);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                logger.info("User profile retrieved successfully, ID: {}", id);
                return new ResponseEntity<>(user, HttpStatus.OK);

            } else {
                logger.warn("User profile not found, ID: {}", id);
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }

        } catch (RuntimeException e) {
            logger.error("Error retrieving user profile, ID: {}", id, e);
            throw new RuntimeException("Error retrieving user profile: " + e.getMessage());
            //return new ResponseEntity<>("Error retrieving user profile: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/update_profile")
    public ResponseEntity<?> updateProfile(@RequestParam long id, @RequestBody User updatedUser) {
        try {
            // Log for debugging
            Optional<User> userOptional = userService.getUserProfile(id);
            if (userOptional.isPresent()){
                System.out.println("Updating profile for user ID: " + id);

            User savedUser = userService.updateProfile(id, updatedUser);

            // Log for debugging
                logger.info("Profile updated successfully: " + savedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
                logger.warn("User not found for ID: " + id);
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        } catch (Exception e) {
            // Log for debugging
            logger.error("Error updating user profile", e);

            return new ResponseEntity<>("Error updating user profile: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /*@PutMapping("/change_password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable long id, @RequestParam String newPassword) {
        userService.changePassword(id, newPassword);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<String> resetPassword(@RequestParam String username, @RequestParam String newPassword) {
        userService.resetPassword(username, newPassword);
        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }*/

    @GetMapping("/allusers")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> userOptional = userService.getAllUsers();
            logger.info("Retrieved all users successfully");
            return new ResponseEntity<>(userOptional, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving all users", e);
            return new ResponseEntity<>("Error retrieving all users: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        try {
            User addedUser = userService.addUser(user);
            logger.info("User added successfully: {}", addedUser);
            return new ResponseEntity<>(addedUser, HttpStatus.CREATED).getBody();
        } catch (Exception e) {
            logger.error("Error adding user", e);
            ResponseEntity<String> stringResponseEntity = new ResponseEntity<>("Error adding user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            //return stringResponseEntity;
        }
        return user;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        try {
            Optional<User> userOptional = userService.getUserProfile(id);

            if (userOptional.isPresent()) {
                userService.deleteUser(id);
                logger.info("User deleted successfully, ID: {}", id);
                return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
            } else {
                logger.warn("User not found, ID: {}", id);
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting user, ID: {}", id, e);
            return new ResponseEntity<>("Error deleting user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
