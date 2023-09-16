/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.RolesDto;
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
    public ResponseEntity<RolesDto> crearPersona_perfil(
            @RequestBody RolesDto rolDto) {
        return new ResponseEntity<>(rolesService.create(rolDto), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<RolesDto> listarPersonas_perfil() {
        return rolesService.findAll();
    }

    @GetMapping("/{codigorol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolesDto> buscarPersona_perfilPorId(
            @PathVariable(value = "codigorol") String codigorol) {
        return ResponseEntity.ok(rolesService.findById(codigorol));
    }

    @PutMapping("/{codigorol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RolesDto> actualizarPersona_perfil(
            @RequestBody RolesDto rolDto,
            @PathVariable(value = "codigorol") String codigorol) {
        return new ResponseEntity<>(rolesService.update(rolDto, codigorol), HttpStatus.OK);
    }

    @DeleteMapping("/{codigorol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarPersona_perfil(
            @PathVariable(value = "codigorol") String codigorol) {
        rolesService.delete(codigorol);
        return new ResponseEntity<>("Persona_perfil eliminado con éxito", HttpStatus.OK);
    }

}
