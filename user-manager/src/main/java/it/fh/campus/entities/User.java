package it.fh.campus.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private final String firstName;
    private final String lastName;
    private final String username;
    private String password;

}
