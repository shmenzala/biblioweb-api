/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.AutoresDto;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.pageable.PageableValues;
import com.pe.sh.Biblioapi.service.AutoresService;
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
@RequestMapping("/api/v1/autores")
public class AutoresController {

    private final AutoresService autoresService;

    public AutoresController(AutoresService autoresService) {
        this.autoresService = autoresService;
    }

    @PostMapping
    public ResponseEntity<AutoresDto> crearAutor(
            @Valid @RequestBody AutoresDto autDto) {
        return new ResponseEntity<>(autoresService.create(autDto), HttpStatus.OK);
    }

    @GetMapping
    public PageableDataDto listarAutoresPaginados(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableValues.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir) {
        return autoresService.findAllPagination(pageNo, pageSize, orderBy, sortDir);
    }
    
    @GetMapping("/all")
    public List<AutoresDto> listarAutores() {
        return autoresService.findAll();
    }

    @GetMapping("/{codigoaut}")
    public ResponseEntity<AutoresDto> buscarAutorPorId(
            @PathVariable(value = "codigoaut") String codigoaut) {
        return ResponseEntity.ok(autoresService.findById(codigoaut));
    }
        
    @GetMapping("/cs/{coincidencia}")
    public PageableDataDto buscarAutorPorCoincidencia(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "codigoaut", required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "coincidencia") String coincidencia) {
        return autoresService.findAllWithCoincidence(coincidencia, pageNo, pageSize, orderBy, sortDir);
    }

    @PutMapping("/{codigoaut}")
    public ResponseEntity<AutoresDto> actualizarAutor(
            @Valid @RequestBody AutoresDto autDto,
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
