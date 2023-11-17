package utfpr.trabalho.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utfpr.trabalho.api.model.tracking.Tracking;
import utfpr.trabalho.api.model.user.UsersModel;
import utfpr.trabalho.api.repository.TrackingRepository;
import utfpr.trabalho.api.repository.UsersRepository;
import utfpr.trabalho.api.service.TrackingService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/tracking")
public class TrackingController {

    private TrackingRepository trackingRepository;
    private UsersRepository usersRepository;

    private TrackingService trackingService;

    /*
    @PostMapping("/save")
    public ResponseEntity<Tracking> save(@RequestBody  Tracking tracking, @RequestHeader("userId") Integer userId){
        System.out.println("tracking save"+tracking.getCodeTracking());
        System.out.println("user id tracking save "+userId);
        Optional<UsersModel> user = usersRepository.findById(userId);
        UsersModel users = user.get();
        tracking.setUser(users);
        return ResponseEntity.ok(trackingRepository.save(tracking));

    }
    */
    @PostMapping("/save")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Tracking> save(@RequestBody Tracking tracking,

        @RequestHeader("Authorization") String authorizationHeader,  @RequestHeader("userId") int userId) {
        try {
            System.out.println("tracking save: " + tracking.getCodeTracking());
            System.out.println("user id tracking save: " + userId);

            Optional<UsersModel> userOptional = usersRepository.findById(userId);

            if (userOptional.isPresent()) {
                UsersModel user = userOptional.get();
                tracking.setUser(user);
                Tracking savedTracking = trackingRepository.save(tracking);

                return ResponseEntity.ok(savedTracking);
            } else {
                // Usuário não encontrado, retorna um status 404 Not Found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Trate exceções específicas ou logue o erro para depuração
            e.printStackTrace();
            // Retorna um status 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/getAllTrackings")
    public ResponseEntity<List<Tracking>> showAllTrackings(@RequestHeader ("userId") Integer userId,
                  @RequestHeader("Authorization") String authorizationHeader){
        System.out.println("user id "+userId);
        List<Tracking> userTrackings = trackingRepository.findByUserId(userId);
        System.out.println("user trackings retorno "+userTrackings);
        return ResponseEntity.ok(userTrackings);

    }

    @GetMapping("/{code}")
    public String trackingObject(@PathVariable String code) {
        return trackingService.trackingOneObject(code);
    }
}
