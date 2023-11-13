package utfpr.trabalho.api.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;


public enum UserRole {
    ADMIN("admin") {
        @Override
        public String getRole() {
            return "admin";
        }
    },
    USER("user") {
        @Override
        public String getRole() {
            return "user";
        }
    };

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public abstract String getRole();


}