package utfpr.trabalho.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import utfpr.trabalho.api.infra.security.TokenService;
import utfpr.trabalho.api.model.tracking.Tracking;
import utfpr.trabalho.api.model.tracking.TrackingRequestDTO;
import utfpr.trabalho.api.model.tracking.TrackingResponseDTO;
import utfpr.trabalho.api.model.user.UsersModel;
import utfpr.trabalho.api.repository.TrackingRepository;
import utfpr.trabalho.api.repository.UsersRepository;
import utfpr.trabalho.api.service.TrackingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tracking")
public class TrackingController {


    @Autowired
    private  TrackingRepository trackingRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;
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
    public ResponseEntity<TrackingResponseDTO> save(@RequestBody TrackingRequestDTO trackingRequestDTO, @RequestHeader("userid") Integer userid) {
        System.out.println("tracking save: " + trackingRequestDTO.codeTracking());
        System.out.println("user id "+userid);
        try {

            UsersModel user = usersRepository.findById(userid).get();
            System.out.println("tracking save: " + trackingRequestDTO.codeTracking());

            Tracking tracking = new Tracking(trackingRequestDTO, user);
            Tracking savedTracking = trackingRepository.save(tracking);
            Integer id = savedTracking.getId();
            String codeTracking = savedTracking.getCodeTracking();
            LocalDateTime createdDateTime = savedTracking.getCreatedAt();
            return ResponseEntity.ok(new TrackingResponseDTO(id, codeTracking, createdDateTime));


        } catch (Exception e) {
            // Trate exceções específicas ou logue o erro para depuração
            e.printStackTrace();
            // Retorna um status 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }



/*
    @GetMapping("/getAllTrackings")
    public ResponseEntity<ResponseEntity<TrackingResponseDTO>> showAllTrackings(@RequestHeader ("userId") Integer userId){
        System.out.println("user id "+userId);
        List<Tracking> userTrackings = trackingRepository.findByUserId(userId);
        System.out.println("user trackings retorno "+userTrackings);
        return ResponseEntity.ok(new TrackingResponseDTO());

    }
    */
        @GetMapping("/getAllTrackings")
        public ResponseEntity<List<TrackingResponseDTO>> showAllTrackings(@RequestHeader("userId") Integer userId) {
            List<Tracking> userTrackings = trackingRepository.findByUserId(userId);


            List<TrackingResponseDTO> trackingDTOs = userTrackings.stream()
                    .map(tracking -> new TrackingResponseDTO(
                            tracking.getId(),
                            tracking.getCodeTracking(),
                            tracking.getCreatedAt()
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(trackingDTOs);
        }


    @GetMapping("/{code}")
    public String trackingObject(@PathVariable String code) {
        return trackingService.trackingOneObject(code);
    }
}
