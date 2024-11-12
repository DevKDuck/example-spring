package com.devkduck.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private String key;

    Role(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
