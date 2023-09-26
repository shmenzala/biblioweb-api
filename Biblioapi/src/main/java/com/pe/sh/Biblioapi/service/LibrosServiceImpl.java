/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.LibrosDto;
import com.pe.sh.Biblioapi.exceptions.ResourceNotFoundException;
import com.pe.sh.Biblioapi.model.Autores;
import com.pe.sh.Biblioapi.model.Editoriales;
import com.pe.sh.Biblioapi.model.Generos;
import com.pe.sh.Biblioapi.model.Libros;
import com.pe.sh.Biblioapi.pageable.PageableDataDto;
import com.pe.sh.Biblioapi.repository.AutoresRepository;
import com.pe.sh.Biblioapi.repository.EditorialesRepository;
import com.pe.sh.Biblioapi.repository.GenerosRepository;
import com.pe.sh.Biblioapi.repository.LibrosRepository;
import java.util.List;
import java.util.Set;
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
public class LibrosServiceImpl extends Mapper<Libros, LibrosDto> implements LibrosService {

    private final LibrosRepository librosRepository;

    private final EditorialesRepository editorialesRepository;

    private final AutoresRepository autoresRepository;

    private final GenerosRepository generosRepository;

    public LibrosServiceImpl(LibrosRepository librosRepository, EditorialesRepository editorialesRepository, AutoresRepository autoresRepository, GenerosRepository generosRepository, ModelMapper modelMapper) {
        super(modelMapper);
        this.librosRepository = librosRepository;
        this.editorialesRepository = editorialesRepository;
        this.autoresRepository = autoresRepository;
        this.generosRepository = generosRepository;
    }

    @Override
    public LibrosDto create(LibrosDto dto, String codigoedi) {
        Libros libros = toEntity(dto, Libros.class);

        Editoriales editorial = editorialesRepository.findById(codigoedi)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "id", codigoedi));

        libros.setNombre(dto.getNombre());
        libros.setEdicion(dto.getEdicion());
        libros.setLugar_publicacion(dto.getLugar_publicacion());
        libros.setFech_publicacion(dto.getFech_publicacion());
        libros.setPaginas(dto.getPaginas());
        libros.setEditorial(editorial);

        Libros nuevoLibro = librosRepository.save(libros);

        return toDto(nuevoLibro, LibrosDto.class);
    }

    @Override
    public List<LibrosDto> findAll() {
        List<Libros> libros = librosRepository.findAll();

        return libros.stream().map(libro -> toDto(libro, LibrosDto.class)).collect(Collectors.toList());
    }

    @Override
    public LibrosDto findById(String id) {
        Libros libros = librosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));

        return toDto(libros, LibrosDto.class);
    }

    //METODO "update" en OBSERVACION
    @Override
    public LibrosDto update(LibrosDto dto, String id, String codigoedi) {
        Libros libros = librosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));

        Editoriales editorial = editorialesRepository.findById(codigoedi)
                .orElseThrow(() -> new ResourceNotFoundException("Editorial", "id", codigoedi));

        libros.setNombre(dto.getNombre());
        libros.setEdicion(dto.getEdicion());
        libros.setLugar_publicacion(dto.getLugar_publicacion());
        libros.setFech_publicacion(dto.getFech_publicacion());
        libros.setPaginas(dto.getPaginas());
        libros.setEditorial(editorial);

        Libros actualizaLibro = librosRepository.save(libros);

        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public void delete(String id) {
        Libros libros = librosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));
        librosRepository.delete(libros);
    }

    @Override
    public LibrosDto addAutores(String id, String codigoaut) {
        Libros libro = librosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));
        Autores autor = autoresRepository.findById(codigoaut)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", codigoaut));

        Set<Autores> autoresDeLibroActual = libro.getAutores();
        autoresDeLibroActual.add(autor);

        libro.setAutores(autoresDeLibroActual);

        Libros actualizaLibro = librosRepository.save(libro);

        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public LibrosDto removeAutores(String id, String codigoaut) {
        Libros libro = librosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));
        Autores autor = autoresRepository.findById(codigoaut)
                .orElseThrow(() -> new ResourceNotFoundException("Autor", "id", codigoaut));

        Set<Autores> autoresDeLibroActual = libro.getAutores();
        autoresDeLibroActual.remove(autor);

        libro.setAutores(autoresDeLibroActual);

        Libros actualizaLibro = librosRepository.save(libro);

        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public LibrosDto addGeneros(String id, String codigogen) {
        Libros libro = librosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));
        Generos genero = generosRepository.findById(codigogen)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", codigogen));

        Set<Generos> generosDeLibroActual = libro.getGeneros();
        generosDeLibroActual.add(genero);

        libro.setGeneros(generosDeLibroActual);

        Libros actualizaLibro = librosRepository.save(libro);

        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public LibrosDto removeGeneros(String id, String codigogen) {
        Libros libro = librosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro", "id", id));
        Generos genero = generosRepository.findById(codigogen)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", "id", codigogen));

        Set<Generos> generosDeLibroActual = libro.getGeneros();
        generosDeLibroActual.remove(genero);

        libro.setGeneros(generosDeLibroActual);

        Libros actualizaLibro = librosRepository.save(libro);

        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public LibrosDto create(LibrosDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LibrosDto update(LibrosDto dto, String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PageableDataDto findAllPagination(int pageNo, int pageSize, String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Libros> librosPage = librosRepository.findAll(pageable);
        
        List<LibrosDto> content = librosPage.getContent().stream().map(libro -> toDto(libro, LibrosDto.class)).collect(Collectors.toList());
        
        PageableDataDto librosResp = new PageableDataDto();
        
        librosResp.setContent(content);
        librosResp.setPageNo(librosPage.getNumber());
        librosResp.setPageSize(librosPage.getSize());
        librosResp.setTotalElements(librosPage.getTotalElements());
        librosResp.setTotalPages(librosPage.getTotalPages());
        librosResp.setLast(librosPage.isLast());
        
        return librosResp;
    }

}
