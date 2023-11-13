package utfpr.trabalho.api.repository;

import utfpr.trabalho.api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
