/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.AutoresDto;
import com.pe.sh.Biblioapi.service.AutoresService;
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
@RequestMapping("/api/v1/autores")
public class AutoresController {

    private final AutoresService autoresService;

    public AutoresController(AutoresService autoresService) {
        this.autoresService = autoresService;
    }

    @PostMapping
    public ResponseEntity<AutoresDto> crearAutor(
            @RequestBody AutoresDto autDto) {
        return new ResponseEntity<>(autoresService.create(autDto), HttpStatus.OK);
    }

    @GetMapping
    public List<AutoresDto> listarAutores() {
        return autoresService.findAll();
    }

    @GetMapping("/{codigoaut}")
    public ResponseEntity<AutoresDto> buscarAutorPorId(
            @PathVariable(value = "codigoaut") String codigoaut) {
        return ResponseEntity.ok(autoresService.findById(codigoaut));
    }

    @PutMapping("/{codigoaut}")
    public ResponseEntity<AutoresDto> actualizarAutor(
            @RequestBody AutoresDto autDto,
            @PathVariable(value = "codugoaut") String codigoaut) {
        return new ResponseEntity<>(autoresService.update(autDto, codigoaut), HttpStatus.OK);
    }

    @DeleteMapping("/{codigoaut}")
    public ResponseEntity<String> eliminarAutor(
            @PathVariable(value = "codugoaut") String codigoaut) {
        autoresService.delete(codigoaut);
        return new ResponseEntity<>("Autor eliminado con éxito", HttpStatus.OK);
    }

}
