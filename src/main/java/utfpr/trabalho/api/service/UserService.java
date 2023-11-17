package utfpr.trabalho.api.service;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utfpr.trabalho.api.model.user.UserRole;
import utfpr.trabalho.api.model.user.UsersModel;
import utfpr.trabalho.api.repository.UsersRepository;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostConstruct
    public void addUserDefault() {
        addDefaultAdmin();
        addDefaultUser();
    }

    public void addDefaultAdmin() {

        LocalDateTime localDateTime = LocalDateTime.now();

        UsersModel userDefaultAdmin = new UsersModel();

        userDefaultAdmin.setId(1);
        userDefaultAdmin.setCpf("12312312311");
        userDefaultAdmin.setFirstName("Admin");
        userDefaultAdmin.setLastName("Admin");
        userDefaultAdmin.setBirthDate(new Date(1991L-10L-25L));
        userDefaultAdmin.setEmail("admin@admin.com.br");
        userDefaultAdmin.setLogin("admin");
        userDefaultAdmin.setPassword(new BCryptPasswordEncoder().encode(userDefaultAdmin.getLogin()));
        userDefaultAdmin.setPhoneNumber("(16)98888-8888");
        userDefaultAdmin.setCreatedAt(localDateTime);
        userDefaultAdmin.setRole(UserRole.ADMIN);
        userDefaultAdmin.setActive(true);

        usersRepository.save(userDefaultAdmin);
    }


    public void addDefaultUser() {

        LocalDateTime localDateTime = LocalDateTime.now();

        UsersModel userDefaultUser = new UsersModel();

        userDefaultUser.setId(2);
        userDefaultUser.setCpf("12312312322");
        userDefaultUser.setFirstName("User");
        userDefaultUser.setLastName("User");
        userDefaultUser.setBirthDate(new Date(1990L-10L-25L));
        userDefaultUser.setEmail("user@user.com.br");
        userDefaultUser.setLogin("user");
        userDefaultUser.setPassword(new BCryptPasswordEncoder().encode(userDefaultUser.getLogin()));
        userDefaultUser.setPhoneNumber("(16)99999-9999");
        userDefaultUser.setCreatedAt(localDateTime);
        userDefaultUser.setRole(UserRole.USER);
        userDefaultUser.setActive(true);

        usersRepository.save(userDefaultUser);
    }
}
