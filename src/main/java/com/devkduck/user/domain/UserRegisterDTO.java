package com.devkduck.user.domain;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO {
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
}
