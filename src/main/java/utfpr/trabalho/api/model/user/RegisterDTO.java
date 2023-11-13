package utfpr.trabalho.api.model.user;

public record RegisterDTO(String login, String password, UserRole role) {
}