package com.group11.campusSafety.Controllers;


import com.group11.campusSafety.Models.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller("/api")
public class ApiController {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity){
        String firstName = userEntity.getFirstName();
        String lastName = userEntity.getLastName();
        String email = userEntity.getEmail();
        String password = userEntity.getPassword();

        System.out.println(firstName + " " + lastName + " " + email + " " + password);

        return ResponseEntity.ok().build();
    }
}
