package utfpr.trabalho.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utfpr.trabalho.api.infra.security.TokenService;
import utfpr.trabalho.api.model.user.*;
import utfpr.trabalho.api.repository.UsersRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsersModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));

    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody     RegisterDTO data) {

        if (this.usersRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        System.out.println("data -> "+data);

        UsersModel newUser = new UsersModel(data.cpf(), data.firstName(), data.lastName(), data.birthDate(),
                data.email(), data.login(), encryptedPassword, data.phoneNumber(), data.role());

        this.usersRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        Optional<UsersModel> usersModelOptional = usersRepository.findById(id);
        System.out.println("user model optional "+usersModelOptional.get().getLogin());
        if(usersModelOptional.isPresent()){
            this.usersRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
