package raf.sk.drugiprojekat.korisnickiservis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
import raf.sk.drugiprojekat.korisnickiservis.listener.helper.MessageHelper;
import raf.sk.drugiprojekat.korisnickiservis.security.CheckSecurity;
import raf.sk.drugiprojekat.korisnickiservis.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(summary = "Get all gym")
    @Parameters({
            @Parameter(name = "page", description = "What page number you want", in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "Number of items to return", in = ParameterIn.QUERY),
            @Parameter(name = "sort", in = ParameterIn.QUERY,
                    description = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @CheckSecurity(roles = {"ROLEADMIN", "ROLEMANAGER"})
    @GetMapping("/all")
    public ResponseEntity<Page<ClientDto>> getAllClients(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Get client by id")
    @GetMapping(params = {"id"})
    public ResponseEntity<ClientDto> getClientById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update client")
    @CheckSecurity(roles = {"ROLEADMIN", "ROLEMANAGER", "ROLECLIENT"}, client_id_required = true)
    @PutMapping(params = {"id"})
    public ResponseEntity<ClientDto> updateClientById(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id, @RequestBody(required = false) @Valid ClientUpdateDto clientUpdateDto) {
        return new ResponseEntity<>(clientService.update(id, clientUpdateDto), HttpStatus.OK);
    }

    @Operation(summary = "Enable/Disable client")
    @CheckSecurity(roles = {"ROLEADMIN"})
    @PutMapping(params = {"id", "forbidden"})
    public ResponseEntity<?> forbiddenById(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id, @RequestParam("forbidden") Boolean forbidden) {
        clientService.forbiddenById(id, forbidden);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Register client")
    @PostMapping("/register")
    public ResponseEntity<ClientDto> registerClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginClient(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete client")
    @CheckSecurity(roles = {"ROLEADMIN", "ROLEMANAGER", "ROLECLIENT"}, client_id_required = true)
    @DeleteMapping(params = {"id"})
    public ResponseEntity<?> deleteClient(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id) {
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Confirm register")
    @GetMapping(value = "/confirm", params = {"id"})
    public ResponseEntity<?> confirmRegister(@RequestParam("id") Long id) {
        clientService.confirmRegister(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
