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
import raf.sk.drugiprojekat.korisnickiservis.service.ManagerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {
    private ManagerService managerService;
    @ApiOperation(value = "Get all managers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<Page<ManagerDto>> getAllManagers(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get manager by id")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> getManagerById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(managerService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update manager")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"}, manager_id_required = true)
    @PutMapping("/{id}")
    public ResponseEntity<ManagerDto> updateManagerById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, @RequestBody @Valid ManagerUpdateDto managerUpdateDto) {
        return new ResponseEntity<>(managerService.update(id, managerUpdateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Register manager")
    @PostMapping("/register")
    public ResponseEntity<ManagerDto> registerManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }
}
