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
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminUpdateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenRequestDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenResponseDto;
import raf.sk.drugiprojekat.korisnickiservis.security.CheckSecurity;
import raf.sk.drugiprojekat.korisnickiservis.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

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
    public ResponseEntity<Page<AdminDto>> getAllAdmins(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(adminService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Get admin by id")
    @CheckSecurity(roles = {"ROLEADMIN"})
    @GetMapping(params = {"id"})
    public ResponseEntity<AdminDto> getAdmin(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id) {
        return new ResponseEntity<>(adminService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update admin by id")
    @CheckSecurity(roles = {"ROLEADMIN"}, admin_id_required = true)
    @PutMapping(params = {"id"})
    public ResponseEntity<AdminDto> updateAdmin(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id, @RequestBody(required = false) @Valid AdminUpdateDto adminUpdateDto) {
        return new ResponseEntity<>(adminService.update(id, adminUpdateDto), HttpStatus.OK);
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginAdmin(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(adminService.login(tokenRequestDto), HttpStatus.OK);
    }
}
