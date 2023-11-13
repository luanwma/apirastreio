package utfpr.trabalho.api.model.user;

import java.util.Date;

public record RegisterDTO(String cpf, String firstName, String lastName, Date birthDate,
                          String email, String login, String password, String phoneNumber, UserRole role                     ) {
}