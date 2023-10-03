/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.EditorialesDto;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Editoriales;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.repository.EditorialesRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class EditorialesServiceImpl extends Mapper<Editoriales, EditorialesDto> implements EditorialesService {

    private final EditorialesRepository editorialesRepository;

    public EditorialesServiceImpl(EditorialesRepository editorialesRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.editorialesRepository = editorialesRepository;
    }

    @Override
    public EditorialesDto create(EditorialesDto dto) {
        Editoriales editoriales = toEntity(dto, Editoriales.class);
        Editoriales nuevoEditorial = editorialesRepository.save(editoriales);

        return toDto(nuevoEditorial, EditorialesDto.class);
    }
    
    @Override
    public PageableDataDto findAllPagination(int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Editoriales> editorialesPage = editorialesRepository.findAll(pageable);
        
        List<EditorialesDto> content = editorialesPage.getContent().stream().map(editorial -> toDto(editorial, EditorialesDto.class)).collect(Collectors.toList());
        
        PageableDataDto editorialesResp = new PageableDataDto();
        
        editorialesResp.setContent(content);
        editorialesResp.setPageNo(editorialesPage.getNumber());
        editorialesResp.setPageSize(editorialesPage.getSize());
        editorialesResp.setTotalElements(editorialesPage.getTotalElements());
        editorialesResp.setTotalPages(editorialesPage.getTotalPages());
        editorialesResp.setLast(editorialesPage.isLast());
        
        return editorialesResp;
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

    @Override
    public PageableDataDto findAllWithCoincidence(String search, int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Editoriales> editorialesPage = editorialesRepository.buscarEditorialPorCoincidencia(search, pageable);
        
        List<EditorialesDto> content = editorialesPage.getContent().stream().map(editorial -> toDto(editorial, EditorialesDto.class)).collect(Collectors.toList());
        
        PageableDataDto editorialesResp = new PageableDataDto();
        
        editorialesResp.setContent(content);
        editorialesResp.setPageNo(editorialesPage.getNumber());
        editorialesResp.setPageSize(editorialesPage.getSize());
        editorialesResp.setTotalElements(editorialesPage.getTotalElements());
        editorialesResp.setTotalPages(editorialesPage.getTotalPages());
        editorialesResp.setLast(editorialesPage.isLast());
        
        return editorialesResp;
    }

}
