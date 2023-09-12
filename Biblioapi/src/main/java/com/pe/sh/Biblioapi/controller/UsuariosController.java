/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.UsuariosDto;
import com.pe.sh.Biblioapi.service.UsuariosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/{codigorol}")
    public ResponseEntity<UsuariosDto> crearUsuario(
            @RequestBody UsuariosDto usuDto,
            @PathVariable(value = "codigorol") String codigorol) {
        return new ResponseEntity<>(usuariosService.create(usuDto, codigorol), HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<UsuariosDto> listarUsuarios() {
        return usuariosService.findAll();
    }

    @GetMapping("/{codigous}")
    public ResponseEntity<UsuariosDto> buscarUsuarioPorId(
            @PathVariable(value = "codigous") String codigous) {
        return ResponseEntity.ok(usuariosService.findById(codigous));
    }

    @PutMapping("/{codigous}")
    public ResponseEntity<UsuariosDto> actualizarUsuario(
            @RequestBody UsuariosDto usuDto,
            @PathVariable(value = "codigous") String codigous) {
        return new ResponseEntity<>(usuariosService.update(usuDto, codigous), HttpStatus.OK);
    }

    @DeleteMapping("/{codigous}")
    public ResponseEntity<String> eliminarUsuario(
            @PathVariable(value = "codigous") String codigous) {
        usuariosService.delete(codigous);
        return new ResponseEntity<>("Usuario eliminado con éxito", HttpStatus.OK);
    }

    //EXTRAS
    @PutMapping("/{codigous}/addRol/{codigorol}")
    public ResponseEntity<UsuariosDto> asignarRolesAlUsuario(
            @PathVariable(value = "codigous") String codigous,
            @PathVariable(value = "codigorol") String codigorol) {
        return new ResponseEntity<>(usuariosService.addRoles(codigous, codigorol), HttpStatus.OK);
    }

    @PutMapping("/{codigous}/removeRol/{codigorol}")
    public ResponseEntity<UsuariosDto> removerRolesAlUsuario(
            @PathVariable(value = "codigous") String codigous,
            @PathVariable(value = "codigorol") String codigorol) {
        return new ResponseEntity<>(usuariosService.removeRoles(codigous, codigorol), HttpStatus.OK);
    }
    
    @PutMapping("/{codigous}/addLib/{codigolib}")
    public ResponseEntity<UsuariosDto> asignarLibrosFavAlUsuario(
            @PathVariable(value = "codigous") String codigous,
            @PathVariable(value = "codigolib") String codigolib) {
        return new ResponseEntity<>(usuariosService.addLibrosFavoritos(codigous, codigolib), HttpStatus.OK);
    }

    @PutMapping("/{codigous}/removeLib/{codigolib}")
    public ResponseEntity<UsuariosDto> removerLibrosFavAlUsuario(
            @PathVariable(value = "codigous") String codigous,
            @PathVariable(value = "codigolib") String codigolib) {
        return new ResponseEntity<>(usuariosService.removeLibrosFavoritos(codigous, codigolib), HttpStatus.OK);
    }

}