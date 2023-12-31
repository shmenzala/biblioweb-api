/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.LibrosDto;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.pageable.PageableValues;
import com.pe.sh.Biblioapi.service.LibrosService;
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
@RequestMapping("/api/v1/libros")
public class LibrosController {

    private final LibrosService librosService;

    public LibrosController(LibrosService librosService) {
        this.librosService = librosService;
    }

    @PostMapping("/{codigoedi}")
    public ResponseEntity<LibrosDto> crearLibro(
            @Valid @RequestBody LibrosDto libDto,
            @PathVariable(value = "codigoedi") String codigoedi) {
        return new ResponseEntity<>(librosService.create(libDto, codigoedi), HttpStatus.CREATED);
    }

    @GetMapping
    public PageableDataDto listarLibrosPaginados(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableValues.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir) {
        return librosService.findAllPagination(pageNo, pageSize, orderBy, sortDir);
    }
    
    @GetMapping("/all")
    public List<LibrosDto> listarLibros() {
        return librosService.findAll();
    }

    @GetMapping("/{codigolib}")
    public ResponseEntity<LibrosDto> buscarLibroPorId(
            @PathVariable(value = "codigolib") String codigolib) {
        return ResponseEntity.ok(librosService.findById(codigolib));
    }
    
    @GetMapping("/cs/{coincidencia}")
    public PageableDataDto buscarLibroPorCoincidencia(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "codigolib", required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "coincidencia") String coincidencia) {
        return librosService.findAllWithCoincidence(coincidencia, pageNo, pageSize, orderBy, sortDir);
    }

    @PutMapping("/{codigolib}/{codigoedi}")
    public ResponseEntity<LibrosDto> actualizarLibro(
            @Valid @RequestBody LibrosDto libDto,
            @PathVariable(value = "codigolib") String codigolib,
            @PathVariable(value = "codigoedi") String codigoedi) {
        return new ResponseEntity<>(librosService.update(libDto, codigolib, codigoedi), HttpStatus.OK);
    }

    @DeleteMapping("/{codigolib}")
    public ResponseEntity<String> eliminarLibro(
            @PathVariable(value = "codigolib") String codigolib) {
        librosService.delete(codigolib);
        return new ResponseEntity<>("Libro eliminado con éxito", HttpStatus.OK);
    }

    //EXTRAS
    @PutMapping("/{codigolib}/addAut/{codigoaut}")
    public ResponseEntity<LibrosDto> asignarAutoresAlLibro(
            @PathVariable(value = "codigolib") String codigolib,
            @PathVariable(value = "codigoaut") String codigoaut) {
        return new ResponseEntity<>(librosService.addAutores(codigolib, codigoaut), HttpStatus.OK);
    }

    @PutMapping("/{codigolib}/removeAut/{codigoaut}")
    public ResponseEntity<LibrosDto> removerAutoresAlLibro(
            @PathVariable(value = "codigolib") String codigolib,
            @PathVariable(value = "codigoaut") String codigoaut) {
        return new ResponseEntity<>(librosService.removeAutores(codigolib, codigoaut), HttpStatus.OK);
    }

    @PutMapping("/{codigolib}/addGen/{codigogen}")
    public ResponseEntity<LibrosDto> asignarGenerosAlLibro(
            @PathVariable(value = "codigolib") String codigolib,
            @PathVariable(value = "codigogen") String codigogen) {
        return new ResponseEntity<>(librosService.addGeneros(codigolib, codigogen), HttpStatus.OK);
    }

    @PutMapping("/{codigolib}/removeGen/{codigogen}")
    public ResponseEntity<LibrosDto> removerGenerosAlLibro(
            @PathVariable(value = "codigolib") String codigolib,
            @PathVariable(value = "codigogen") String codigogen) {
        return new ResponseEntity<>(librosService.removeGeneros(codigolib, codigogen), HttpStatus.OK);
    }

}
