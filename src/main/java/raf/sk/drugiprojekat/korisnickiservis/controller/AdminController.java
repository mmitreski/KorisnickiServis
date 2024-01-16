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
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.AdminUpdateDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenRequestDto;
import raf.sk.drugiprojekat.korisnickiservis.dto.TokenResponseDto;
import raf.sk.drugiprojekat.korisnickiservis.security.CheckSecurity;
import raf.sk.drugiprojekat.korisnickiservis.service.AdminService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

    @ApiOperation(value = "Get all admins")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping("/all")
    public ResponseEntity<Page<AdminDto>> getAllAdmins(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(adminService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get admin by id")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping(params = {"id"})
    public ResponseEntity<AdminDto> getAdmin(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id) {
        return new ResponseEntity<>(adminService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update admin by id")
    @CheckSecurity(roles = {"ROLE_ADMIN"}, admin_id_required = true)
    @PutMapping(params = {"id"})
    public ResponseEntity<AdminDto> updateAdmin(@RequestHeader("Authorization") String authorization, @RequestParam("id") Long id, @RequestBody(required = false) @Valid AdminUpdateDto adminUpdateDto) {
        return new ResponseEntity<>(adminService.update(id, adminUpdateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginAdmin(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(adminService.login(tokenRequestDto), HttpStatus.OK);
    }
}
