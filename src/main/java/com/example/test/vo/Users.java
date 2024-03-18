package com.example.test.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Users {
    private int no;
    private String id;
    private String password;
    private String name;
    private String email;
}
