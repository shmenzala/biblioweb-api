/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.EditorialesDto;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.pageable.PageableValues;
import com.pe.sh.Biblioapi.service.EditorialesService;
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
@RequestMapping("/api/v1/editoriales")
public class EditorialesController {

    private final EditorialesService editorialesService;

    public EditorialesController(EditorialesService editorialesService) {
        this.editorialesService = editorialesService;
    }

    @PostMapping
    public ResponseEntity<EditorialesDto> crearEditorial(
            @Valid @RequestBody EditorialesDto ediDto) {
        return new ResponseEntity<>(editorialesService.create(ediDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PageableDataDto listarEditorialesPaginados(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageableValues.DEFAULT_ORDER_BY, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir) {
        return editorialesService.findAllPagination(pageNo, pageSize, orderBy, sortDir);
    }

    @GetMapping("/all")
    public List<EditorialesDto> listarEditoriales() {
        return editorialesService.findAll();
    }

    @GetMapping("/{codigoedi}")
    public ResponseEntity<EditorialesDto> buscarEditorialPorId(
            @PathVariable(value = "codigoedi") String codigoedi) {
        return ResponseEntity.ok(editorialesService.findById(codigoedi));
    }

    @GetMapping("/cs/{coincidencia}")
    public PageableDataDto buscarEditorialPorCoincidencia(
            @RequestParam(value = "pageNo", defaultValue = PageableValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "codigoedi", required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = PageableValues.DEFAULT_ORDER_DIRECTION, required = false) String sortDir,
            @PathVariable(value = "coincidencia") String coincidencia) {
        return editorialesService.findAllWithCoincidence(coincidencia, pageNo, pageSize, orderBy, sortDir);
    }

    @PutMapping("/{codigoedi}")
    public ResponseEntity<EditorialesDto> actualizarEditorial(
            @Valid @RequestBody EditorialesDto ediDto,
            @PathVariable(value = "codigoedi") String codigoedi) {
        return new ResponseEntity<>(editorialesService.update(ediDto, codigoedi), HttpStatus.OK);
    }

    @DeleteMapping("/{codigoedi}")
    public ResponseEntity<String> eliminarEditorial(
            @PathVariable(value = "codigoedi") String codigoedi) {
        editorialesService.delete(codigoedi);
        return new ResponseEntity<>("Editorial eliminado con éxito", HttpStatus.OK);
    }

}
