package med.voli.api.domain.user.dto;

import med.voli.api.domain.user.User;

public record ResponseUserDto(Long id, String username) {
    public ResponseUserDto(User user) {
        this(user.getId(), user.getUsername());
    }
}
