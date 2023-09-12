/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.EditorialesDto;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Editoriales;
import com.pe.sh.Biblioapi.repository.EditorialesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class EditorialesServiceImpl extends Mapper<Editoriales, EditorialesDto> implements EditorialesService {

    @Autowired
    private EditorialesRepository editorialesRepository;

    @Override
    public EditorialesDto create(EditorialesDto dto) {
        Editoriales editoriales = toEntity(dto, Editoriales.class);
        Editoriales nuevoEditorial = editorialesRepository.save(editoriales);

        return toDto(nuevoEditorial, EditorialesDto.class);
    }

    @Override
    public List<EditorialesDto> findAll() {
        List<Editoriales> editoriales = editorialesRepository.findAll();

        return editoriales.stream().map(editorial -> toDto(editorial, EditorialesDto.class)).collect(Collectors.toList());
    }

    @Override
    public EditorialesDto findById(String id) {
        Editoriales editoriales = editorialesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "id", id));

        return toDto(editoriales, EditorialesDto.class);
    }

    @Override
    public EditorialesDto update(EditorialesDto dto, String id) {
        Editoriales editoriales = editorialesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "id", id));

        editoriales.setNombre(dto.getNombre());

        Editoriales actualizaEditorial = editorialesRepository.save(editoriales);

        return toDto(actualizaEditorial, EditorialesDto.class);
    }

    @Override
    public void delete(String id) {
        Editoriales editoriales = editorialesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "id", id));
        editorialesRepository.delete(editoriales);
    }

}
