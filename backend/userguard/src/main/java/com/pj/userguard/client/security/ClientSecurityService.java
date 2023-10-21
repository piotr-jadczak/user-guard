package com.pj.userguard.client.security;

import com.pj.userguard.client.command.CreateClientCommand;
import com.pj.userguard.client.command.CreateClientConfigurationCommand;
import com.pj.userguard.client.entity.Client;
import com.pj.userguard.client.entity.ClientConfiguration;
import com.pj.userguard.client.exception.ClientNotFoundException;
import com.pj.userguard.client.field.ClientId;
import com.pj.userguard.client.field.UniqueId;
import com.pj.userguard.client.repository.ClientFinder;
import com.pj.userguard.client.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientSecurityService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientFinder clientFinder;

    ClientSecurityService(ClientRepository clientRepository, PasswordEncoder passwordEncoder,
                          ClientFinder clientFinder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientFinder = clientFinder;
    }

    @Transactional
    @Override
    public void save(RegisteredClient registeredClient) {
        var createConfigurationCommand = CreateClientConfigurationCommand.of(registeredClient);
        var clientConfiguration = ClientConfiguration.create(createConfigurationCommand);

        var createClientCommand = CreateClientCommand.of(registeredClient, passwordEncoder, clientConfiguration);
        var client = Client.createClient(createClientCommand);

        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public RegisteredClient findById(String id) {
        var uniqueId = UniqueId.of(id);

        return clientFinder.findByUniqueId(uniqueId)
                .map(Client::toRegisteredClient)
                .orElseThrow(() -> new ClientNotFoundException(uniqueId));
    }

    @Transactional(readOnly = true)
    @Override
    public RegisteredClient findByClientId(String clientId) {
        var clientIdentity = ClientId.from(clientId);

        return clientFinder.findByClientId(clientIdentity)
                .map(Client::toRegisteredClient)
                .orElseThrow(() -> new ClientNotFoundException(clientIdentity));
    }
}
