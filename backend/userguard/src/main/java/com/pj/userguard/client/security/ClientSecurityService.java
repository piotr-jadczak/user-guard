package com.pj.userguard.client.security;

import com.pj.userguard.client.service.ClientCreationService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientSecurityService implements RegisteredClientRepository {

    private final ClientCreationService clientCreationService;
    private final ClientDataService clientDataService;

    @Transactional
    @Override
    public void save(RegisteredClient registeredClient) {
        clientCreationService.createClient(registeredClient);
    }

    @Transactional(readOnly = true)
    @Override
    public RegisteredClient findById(String id) {
        return clientDataService.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public RegisteredClient findByClientId(String clientId) {
        return clientDataService.findByClientId(clientId);
    }
}
