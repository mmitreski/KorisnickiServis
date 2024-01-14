package raf.sk.drugiprojekat.korisnickiservis.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
import raf.sk.drugiprojekat.korisnickiservis.security.CheckSecurity;
import raf.sk.drugiprojekat.korisnickiservis.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;
    @ApiOperation(value = "Get all clients")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping
    public ResponseEntity<Page<ClientDto>> getAllClients(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get client by id")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update client")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_CLIENT"}, client_id_required = true)
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClientById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, @RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        return new ResponseEntity<>(clientService.update(id, clientUpdateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Register client")
    @PostMapping("/register")
    public ResponseEntity<ClientDto> registerClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(clientService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginClient(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(clientService.login(tokenRequestDto), HttpStatus.OK);
    }

}
