/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.GenerosDto;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.pageable.PageableValues;
import com.pe.sh.Biblioapi.service.GenerosService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
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
            @Valid @RequestBody GenerosDto genDto) {
        return new ResponseEntity<>(generosService.create(genDto), HttpStatus.OK);
    }

    @GetMapping
    public PageableDataDto listarGenerosPaginados(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableValues.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir) {
        return generosService.findAllPagination(pageNo, pageSize, orderBy, sortDir);
    }
    
    @GetMapping("/all")
    public List<GenerosDto> listarGeneros() {
        return generosService.findAll();
    }

    @GetMapping("/{codigogen}")
    public ResponseEntity<GenerosDto> buscarGeneroPorId(
            @PathVariable(value = "codigogen") String codigogen) {
        return ResponseEntity.ok(generosService.findById(codigogen));
    }

    @GetMapping("/cs/{coincidencia}")
    public PageableDataDto buscarGeneroPorCoincidencia(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "codigogen", required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "coincidencia") String coincidencia) {
        return generosService.findAllWithCoincidence(coincidencia, pageNo, pageSize, orderBy, sortDir);
    }
    
    @PutMapping("/{codigogen}")
    public ResponseEntity<GenerosDto> actualizarGenero(
            @Valid @RequestBody GenerosDto genDto,
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
