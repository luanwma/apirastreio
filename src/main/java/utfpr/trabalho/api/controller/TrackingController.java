package utfpr.trabalho.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.trabalho.api.model.tracking.Tracking;
import utfpr.trabalho.api.model.user.UsersModel;
import utfpr.trabalho.api.repository.TrackingRepository;
import utfpr.trabalho.api.repository.UsersRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tracking")
public class TrackingController {


    private final TrackingRepository trackingRepository;


    @PostMapping("/save")
    public ResponseEntity<Tracking> save(@RequestBody Tracking tracking){
        return ResponseEntity.ok(trackingRepository.save(tracking));
    }

    @GetMapping("/getAllTrackings")
    public ResponseEntity<List<Tracking>> showAllTrackings(){
        return ResponseEntity.ok(trackingRepository.findAll());
    }





}
