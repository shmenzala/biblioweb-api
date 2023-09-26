/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.RolesDto;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.pageable.PageableValues;
import com.pe.sh.Biblioapi.service.RolesService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shmen
 */
@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolesDto> crearRol(
            @RequestBody RolesDto rolDto) {
        return new ResponseEntity<>(rolesService.create(rolDto), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PageableDataDto listarRolesPaginados(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableValues.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir) {
        return rolesService.findAllPagination(pageNo, pageSize, orderBy, sortDir);
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RolesDto> listarRoles() {
        return rolesService.findAll();
    }

    @GetMapping("/{codigorol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolesDto> buscarRolPorId(
            @PathVariable(value = "codigorol") String codigorol) {
        return ResponseEntity.ok(rolesService.findById(codigorol));
    }

    @PutMapping("/{codigorol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolesDto> actualizarRol(
            @RequestBody RolesDto rolDto,
            @PathVariable(value = "codigorol") String codigorol) {
        return new ResponseEntity<>(rolesService.update(rolDto, codigorol), HttpStatus.OK);
    }

    @DeleteMapping("/{codigorol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarRol(
            @PathVariable(value = "codigorol") String codigorol) {
        rolesService.delete(codigorol);
        return new ResponseEntity<>("Persona_perfil eliminado con éxito", HttpStatus.OK);
    }

}
