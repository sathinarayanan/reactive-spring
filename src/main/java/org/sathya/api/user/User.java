package org.sathya.api.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String userName;
    private String email;
    private String mobileNo;

    public User(String name, String userName, String email, String mobileNo) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "";
    }
}
