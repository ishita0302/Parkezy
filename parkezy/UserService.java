package com.parkezy.parkezy;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository  userRepository;
    //private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    /*public void registerUser(User newUser) {
        // You might want to add validation logic here
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    public Optional<User> loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return userOptional;}
        }
        return Optional.empty();
    }*/

    public Optional<User> getUserProfile(long userId) {
        return userRepository.findById(userId);
    }

    /*@Autowired
    public void updateProfile(long userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(""+userId);
        userOptional.ifPresent(user -> {
            // You might want to add validation logic here
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setProfileInformation(updatedUser.getProfileInformation());
        });
    }

    public void changePassword(long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            // You might want to add validation logic here
            user.setPassword(passwordEncoder.encode(newPassword));
        });
    }

    public void resetPassword(String username, String newPassword) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.ifPresent(user -> {
            // You might want to add validation logic here
            user.setPassword(passwordEncoder.encode(newPassword));
        });
    }*/

    public User addUser(User user){
        return (User) userRepository.save(user);
    }
    public User updateProfile(long id, User updatedUser) {
        Optional<User> optionalUser = getUserProfile(id) ;

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update fields based on what you want to allow to be updated
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setProfileInformation(updatedUser.getProfileInformation());

            return userRepository.save(existingUser);
            //return String.valueOf(new ResponseEntity<>("Profile updated successfully", HttpStatus.OK));
        } else {
            return null;
        }
    }
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
