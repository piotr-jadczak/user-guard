package com.pj.userguard.client.security;

import com.pj.userguard.client.entity.Client;
import com.pj.userguard.client.exception.ClientNotFoundException;
import com.pj.userguard.client.field.ClientId;
import com.pj.userguard.client.field.UniqueId;
import com.pj.userguard.client.repository.ClientFinder;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class ClientDataService {

    private final ClientFinder clientFinder;

    public RegisteredClient findById(String id) {
        var uniqueId = UniqueId.of(id);

        return clientFinder.findByUniqueId(uniqueId)
                .map(Client::toRegisteredClient)
                .orElseThrow(() -> new ClientNotFoundException(uniqueId));
    }

    public RegisteredClient findByClientId(String clientId) {
        var clientIdentity = ClientId.from(clientId);

        return clientFinder.findByClientId(clientIdentity)
                .map(Client::toRegisteredClient)
                .orElseThrow(() -> new ClientNotFoundException(clientIdentity));
    }
}
