package com.parking.space.parkingspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ParkingService {
    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    //methods and services
    public Optional<Parking> getSpace(long userId) {
        return parkingRepository.findById(userId);
    }
    public List<Parking> getAllSpaces() {
        return parkingRepository.findAll();
    }
    public Parking addSpace(Parking user){
        return (Parking) parkingRepository.save(user);
    }
    public void deleteSpace(long id) {
        parkingRepository.deleteById(id);
    }
    public Parking updateSpace(long id, Parking updatedUser) {
        Optional<Parking> optionalUser = getSpace(id) ;

        if (optionalUser.isPresent()) {
            Parking existingUser = optionalUser.get();

            // Update fields based on what you want to allow to be updated
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setLocation(updatedUser.getLocation());
            existingUser.setAvailable(updatedUser.getAvailable());
            existingUser.setPrice(updatedUser.getPrice());

            return parkingRepository.save(existingUser);
            //return String.valueOf(new ResponseEntity<>("Profile updated successfully", HttpStatus.OK));
        } else {
            return null;
        }
    }
}
