/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.Personas_perfilDto;
import com.pe.sh.Biblioapi.service.Personas_perfilService;
import java.util.List;
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
@RequestMapping("/api/v1/ppl")
public class Personas_perfilController {

    private final Personas_perfilService personas_perfilService;

    public Personas_perfilController(Personas_perfilService personas_perfilService) {
        this.personas_perfilService = personas_perfilService;
    }

    @PostMapping
    public ResponseEntity<Personas_perfilDto> crearPersona_perfil(
            @RequestBody Personas_perfilDto pplDto) {
        return new ResponseEntity<>(personas_perfilService.create(pplDto), HttpStatus.OK);
    }

    @GetMapping
    public List<Personas_perfilDto> listarPersonas_perfil() {
        return personas_perfilService.findAll();
    }

    @GetMapping("/{codigoppl}")
    public ResponseEntity<Personas_perfilDto> buscarPersona_perfilPorId(
            @PathVariable(value = "codigoppl") String codigoppl) {
        return ResponseEntity.ok(personas_perfilService.findById(codigoppl));
    }

    @PutMapping("/{codigoppl}")
    public ResponseEntity<Personas_perfilDto> actualizarPersona_perfil(
            @RequestBody Personas_perfilDto pplDto,
            @PathVariable(value = "codigoppl") String codigoppl) {
        return new ResponseEntity<>(personas_perfilService.update(pplDto, codigoppl), HttpStatus.OK);
    }

    @DeleteMapping("/{codigoppl}")
    public ResponseEntity<String> eliminarPersona_perfil(
            @PathVariable(value = "codigoppl") String codigoppl) {
        personas_perfilService.delete(codigoppl);
        return new ResponseEntity<>("Persona_perfil eliminado con éxito", HttpStatus.OK);
    }

}
