/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.controller;

import com.pe.sh.Biblioapi.dto.EditorialesDto;
import com.pe.sh.Biblioapi.service.EditorialesService;
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
@RequestMapping("/api/v1/editoriales")
public class EditorialesController {

    private final EditorialesService editorialesService;

    public EditorialesController(EditorialesService editorialesService) {
        this.editorialesService = editorialesService;
    }

    @PostMapping
    public ResponseEntity<EditorialesDto> crearEditorial(
            @RequestBody EditorialesDto ediDto) {
        return new ResponseEntity<>(editorialesService.create(ediDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<EditorialesDto> listarEditoriales() {
        return editorialesService.findAll();
    }

    @GetMapping("/{codigoedi}")
    public ResponseEntity<EditorialesDto> buscarEditorialPorId(
            @PathVariable(value = "codigoedi") String codigoedi) {
        return ResponseEntity.ok(editorialesService.findById(codigoedi));
    }

    @PutMapping("/{codigoedi}")
    public ResponseEntity<EditorialesDto> actualizarEditorial(
            @RequestBody EditorialesDto ediDto,
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
