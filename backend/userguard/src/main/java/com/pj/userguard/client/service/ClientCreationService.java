package com.pj.userguard.client.service;

import com.pj.userguard.client.command.CreateClientCommand;
import com.pj.userguard.client.command.CreateClientConfigurationCommand;
import com.pj.userguard.client.dto.CreateDefaultClientDTO;
import com.pj.userguard.client.dto.CreatedClientDTO;
import com.pj.userguard.client.entity.Client;
import com.pj.userguard.client.entity.ClientConfiguration;
import com.pj.userguard.client.exception.ClientWithClientIdAlreadyExistsException;
import com.pj.userguard.client.exception.ClientWithIdAlreadyExistsException;
import com.pj.userguard.client.exception.DefaultClientConfigurationNotFound;
import com.pj.userguard.client.repository.ClientConfigurationFinder;
import com.pj.userguard.client.repository.ClientFinder;
import com.pj.userguard.client.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class ClientCreationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientFinder clientFinder;
    private final ClientConfigurationFinder clientConfigurationFinder;

    public CreatedClientDTO createDefaultClient(CreateDefaultClientDTO dto) {
        var clientConfiguration = clientConfigurationFinder.findDefaultConfiguration()
                .orElseThrow(DefaultClientConfigurationNotFound::new);

        var createClientCommand = CreateClientCommand.of(dto, passwordEncoder, clientConfiguration);

        return this.createClient(createClientCommand);
    }

    public void createClient(RegisteredClient registeredClient) {
        var createConfigurationCommand = CreateClientConfigurationCommand.of(registeredClient);

        var clientConfiguration = ClientConfiguration.create(createConfigurationCommand);

        var createClientCommand = CreateClientCommand.of(registeredClient, passwordEncoder, clientConfiguration);

        this.createClient(createClientCommand);
    }

    private CreatedClientDTO createClient(CreateClientCommand clientCommand) {
        validateIdUnique(clientCommand);
        validateClientIdUnique(clientCommand);

        var client = Client.createClient(clientCommand);

        clientRepository.save(client);

        return CreatedClientDTO.from(client);
    }

    private void validateIdUnique(CreateClientCommand command) {
        if (clientFinder.existsByUniqueId(command.uniqueId())) {
            throw new ClientWithIdAlreadyExistsException(command.uniqueId());
        }
    }

    private void validateClientIdUnique(CreateClientCommand command) {
        if (clientFinder.existsByClientId(command.clientId())) {
            throw new ClientWithClientIdAlreadyExistsException(command.clientId());
        }
    }
}
