package com.scm.scm20.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {

    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
}
