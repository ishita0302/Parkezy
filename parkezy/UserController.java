package com.parkezy.parkezy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

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

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable long id) {
        Optional<User> userOptional = userService.getUserProfile(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        //return new ResponseEntity<>("ok",HttpStatus.OK);
    }


    @PutMapping("/update_profile")
    public ResponseEntity<User> updateProfile(@RequestParam long id, @RequestBody User updatedUser) {
        User savedUser=userService.updateProfile(id, updatedUser);
        System.out.println(savedUser);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
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
        List<User> userOptional = userService.getAllUsers();
        //return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                //.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(userOptional,HttpStatus.OK);
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        Optional<User> userOptional = userService.getUserProfile(id);

        if (userOptional.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
