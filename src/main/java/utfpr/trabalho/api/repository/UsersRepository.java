package utfpr.trabalho.api.repository;

import utfpr.trabalho.api.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersModel, Integer> {

}
