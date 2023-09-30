package com.pj.userguard.user.api;

import com.pj.userguard.user.dto.CreateUserDTO;
import com.pj.userguard.user.dto.CreatedUserDTO;
import com.pj.userguard.user.service.UserCreationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserCreationService userCreationService;

    @PostMapping
    public ResponseEntity<CreatedUserDTO> createUser(@RequestBody CreateUserDTO dto) {
        return new ResponseEntity<>(userCreationService.createUser(dto), HttpStatus.CREATED);
    }
}
