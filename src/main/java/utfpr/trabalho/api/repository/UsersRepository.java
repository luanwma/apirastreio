package utfpr.trabalho.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import utfpr.trabalho.api.model.user.UsersModel;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
    UserDetails findByLogin(String login);

    void deleteById(Integer id);
}