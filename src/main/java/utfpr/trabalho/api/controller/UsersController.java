package utfpr.trabalho.api.controller;

import utfpr.trabalho.api.model.UsersModel;
import utfpr.trabalho.api.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(repository.save(user));
    }

    @PutMapping("/del/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
