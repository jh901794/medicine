package com.medicine.medicine.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    MEMBER("ROLE_MEMBER");

    private String value;
}