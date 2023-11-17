package utfpr.trabalho.api.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import utfpr.trabalho.api.model.tracking.Tracking;
import utfpr.trabalho.api.model.tracking.TrackingRequestDTO;
import utfpr.trabalho.api.model.tracking.TrackingResponseDTO;
import utfpr.trabalho.api.model.user.UsersModel;
import utfpr.trabalho.api.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UsersController {

    private final UsersRepository repository;


    @GetMapping("/showAll")
    public ResponseEntity<List<UsersModel>> showAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<UsersModel> save(@RequestBody UsersModel user) {
        System.out.println("chegou aqui");
        return ResponseEntity.ok(repository.save(user));
    }

    @PutMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
