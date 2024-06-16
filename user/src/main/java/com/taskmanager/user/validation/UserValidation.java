package com.taskmanager.user.validation;

import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    public Boolean isValidPhone(String phone){
        return phone.matches("(5-9){1}(0-9){9}");
    }

}
