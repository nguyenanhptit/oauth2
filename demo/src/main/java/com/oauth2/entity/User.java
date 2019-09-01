package com.oauth2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    String id;
    String email;
    boolean verifiedEmail;
    String name;
    String picture;
    String skype;
    String hd;
    String phone;
    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    Token token;

}
