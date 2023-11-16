package utfpr.trabalho.api.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.trabalho.api.model.tracking.Tracking;
import utfpr.trabalho.api.model.user.UsersModel;
import utfpr.trabalho.api.repository.TrackingRepository;
import utfpr.trabalho.api.repository.UsersRepository;
import utfpr.trabalho.api.service.TrackingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tracking")
public class TrackingController {


    private final TrackingRepository trackingRepository;

    private TrackingService trackingService;

    @PostMapping("/save")
    public ResponseEntity<Tracking> save(@RequestBody Tracking tracking){
        return ResponseEntity.ok(trackingRepository.save(tracking));
    }

    @GetMapping("/getAllTrackings")
    public ResponseEntity<List<Tracking>> showAllTrackings(){
        return ResponseEntity.ok(trackingRepository.findAll());
    }

    @GetMapping("/{code}")
    public String trackingObject(@PathVariable String code) {
        return trackingService.trackingOneObject(code);
    }
}
