package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO implements DTOValidation<User>{

    private int id = -1;
    private String fname;
    private String lname;
    private String uname;
    private String sex;
    private String password;

    public UserDTO(User user){

        setId (user.getId());
        setFname(user.getFirstName());
        setLname(user.getLastName());
        setSex(user.getGender());
        setUname(user.getUserName());
        setPassword(user.getPassword());
    }

    @Override
    public boolean hasAllValidMappings() {

        return (
            hasValid(fname)
                && hasValid(lname)
                && hasValid(sex)
                && hasValid(uname)
                && hasValid(password)
        );
    }

    @Override
    public boolean hasAnyValidMappings() {
        return (
            hasValid(fname)
                || hasValid(lname)
                || hasValid(sex)
                || hasValid(uname)
                || hasValid(password)
        );
    }

    @Override
    public User createEntity() {

        User user = new User();

        if(hasValid(id)){
            user.setId(id);
        }

        user.setFirstName(fname);
        user.setLastName(lname);
        user.setUserName(uname);
        user.setPassword(password);
        user.setGender(sex);

        return user;
    }

    @Override
    public User updateEntity(User user) {

        if(hasValid(fname)){
            user.setFirstName(fname);
        }

        if(hasValid(lname)){
            user.setLastName(lname);
        }

        if(hasValid(uname)){
            user.setUserName(uname);
        }

        if(hasValid(sex)){
            user.setGender(sex);
        }

        if(hasValid(password)){
            user.setPassword(password);
        }

        return user;
    }

    @Override
    public boolean hasValid(String item) {
        return (!(item == null) && !(item.trim().length() == 0));
    }

    @Override
    public boolean hasValid(Integer item) {
        return item >= 1;
    }
}
