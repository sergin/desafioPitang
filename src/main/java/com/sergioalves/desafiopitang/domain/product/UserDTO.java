package com.sergioalves.desafiopitang.domain.product;

public record UserDTO(
        String firstName,
        String lastName,
        String email,
        String birthday,
        String login,
        String password,
        String phone,

        UserRole role
        ) {
}
