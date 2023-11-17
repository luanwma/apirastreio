package utfpr.trabalho.api.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import utfpr.trabalho.api.infra.security.TokenService;
import utfpr.trabalho.api.model.user.*;
import utfpr.trabalho.api.repository.UsersRepository;
import utfpr.trabalho.api.service.TokenBlacklistService;

import javax.validation.Valid;
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

    private final TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UsersModel) auth.getPrincipal());
        System.out.println(token);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {

        if (this.usersRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        UsersModel newUser = new UsersModel(data.cpf(), data.firstName(), data.lastName(), data.birthDate(),
                data.email(), data.login(), encryptedPassword, data.phoneNumber(), data.role());

        this.usersRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        Optional<UsersModel> userOptional = usersRepository.findById(id);

        if (userOptional.isPresent()) {
            usersRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authToken = extractToken(request);

        if (authToken == null || tokenBlacklistService.isTokenBlacklisted(authToken)) {
            return ResponseEntity.badRequest().body("Invalid or already blacklisted token");
        }

        tokenBlacklistService.blacklistToken(authToken);
        return ResponseEntity.ok("Logged out successfully");
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public AuthenticationController(TokenBlacklistService tokenBlacklistService) {
        this.tokenBlacklistService = tokenBlacklistService;
    }
}
