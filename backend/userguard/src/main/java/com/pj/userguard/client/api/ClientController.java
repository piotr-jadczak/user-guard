package com.pj.userguard.client.api;

import com.pj.userguard.client.dto.CreateDefaultClientDTO;
import com.pj.userguard.client.dto.CreatedClientDTO;
import com.pj.userguard.client.service.ClientCreationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientCreationService clientCreationService;

    @PostMapping
    public ResponseEntity<CreatedClientDTO> createDefaultClient(@RequestBody CreateDefaultClientDTO dto) {
        return new ResponseEntity<>(clientCreationService.createDefaultClient(dto), HttpStatus.CREATED);
    }
}
