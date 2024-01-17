package raf.sk.drugiprojekat.korisnickiservis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.drugiprojekat.korisnickiservis.dto.*;
import raf.sk.drugiprojekat.korisnickiservis.security.CheckSecurity;
import raf.sk.drugiprojekat.korisnickiservis.service.ManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {
    private ManagerService managerService;
    @Operation(summary = "Get all gym")
    @Parameters({
            @Parameter(name = "page", description = "What page number you want", in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "Number of items to return", in = ParameterIn.QUERY),
            @Parameter(name = "sort", in = ParameterIn.QUERY,
                    description = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @CheckSecurity(roles = {"ROLEADMIN"})
    @GetMapping("/all")
    public ResponseEntity<Page<ManagerDto>> getAllManagers(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Get manager by id")
    @GetMapping(params = {"id"})
    public ResponseEntity<ManagerDto> getManagerById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(managerService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update manager")
    @CheckSecurity(roles = {"ROLEADMIN", "ROLEMANAGER"}, manager_id_required = true)
    @PutMapping(params = {"id"})
    public ResponseEntity<ManagerDto> updateManagerById(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id, @RequestBody(required = false) @Valid ManagerUpdateDto managerUpdateDto) {
        return new ResponseEntity<>(managerService.update(id, managerUpdateDto), HttpStatus.OK);
    }

    @Operation(summary = "Register manager")
    @PostMapping("/register")
    public ResponseEntity<ManagerDto> registerManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.OK);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete manager")
    @CheckSecurity(roles = {"ROLEADMIN", "ROLEMANAGER"}, manager_id_required = true)
    @DeleteMapping(params = {"id"})
    public ResponseEntity<?> deleteClient(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id) {
        managerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
