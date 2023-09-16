/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.GenerosDto;
import com.pe.sh.Biblioapi.service.GenerosService;
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
@RequestMapping("/api/v1/generos")
public class GenerosController {

    private final GenerosService generosService;
    
    public GenerosController(GenerosService generosService) {
        this.generosService = generosService;
    }

    @PostMapping
    public ResponseEntity<GenerosDto> crearGenero(
            @RequestBody GenerosDto genDto) {
        return new ResponseEntity<>(generosService.create(genDto), HttpStatus.OK);
    }

    @GetMapping
    public List<GenerosDto> listarGeneros() {
        return generosService.findAll();
    }

    @GetMapping("/{codigogen}")
    public ResponseEntity<GenerosDto> buscarGeneroPorId(
            @PathVariable(value = "codigogen") String codigogen) {
        return ResponseEntity.ok(generosService.findById(codigogen));
    }

    @PutMapping("/{codigogen}")
    public ResponseEntity<GenerosDto> actualizarGenero(
            @RequestBody GenerosDto genDto,
            @PathVariable(value = "codigogen") String codigogen) {
        return new ResponseEntity<>(generosService.update(genDto, codigogen), HttpStatus.OK);
    }

    @DeleteMapping("/{codigogen}")
    public ResponseEntity<String> eliminarGenero(
            @PathVariable(value = "codigogen") String codigogen) {
        generosService.delete(codigogen);
        return new ResponseEntity<>("Genero eliminado con éxito", HttpStatus.OK);
    }

}
