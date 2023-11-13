package utfpr.trabalho.api.controller;

import utfpr.trabalho.api.model.UserModel;
import utfpr.trabalho.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository repository;

    @GetMapping("/showAll")
    public ResponseEntity<List<UserModel>> showAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/save")
    public ResponseEntity<UserModel> save(@RequestBody UserModel user) {
        return ResponseEntity.ok(repository.save(user));
    }

    @PutMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
