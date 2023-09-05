/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.sh.Biblioapi.service;

import com.pe.sh.Biblioapi.configuration.Mapper;
import com.pe.sh.Biblioapi.dto.LibrosDto;
import com.pe.sh.Biblioapi.model.Autores;
import com.pe.sh.Biblioapi.model.Editoriales;
import com.pe.sh.Biblioapi.model.Generos;
import com.pe.sh.Biblioapi.model.Libros;
import com.pe.sh.Biblioapi.repository.AutoresRepository;
import com.pe.sh.Biblioapi.repository.EditorialesRepository;
import com.pe.sh.Biblioapi.repository.GenerosRepository;
import com.pe.sh.Biblioapi.repository.LibrosRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shmen
 */
@Service
public class LibrosServiceImpl extends Mapper<Libros, LibrosDto> implements LibrosService {

    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private EditorialesRepository editorialesRepository;
    
    @Autowired
    private AutoresRepository autoresRepository;
    
    @Autowired
    private GenerosRepository generosRepository;

    @Override
    public LibrosDto create(LibrosDto dto, String codigoedi) {
        Libros libros = toEntity(dto, Libros.class);

        Editoriales editorial = editorialesRepository.findById(codigoedi).orElseThrow(null);

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
        Libros libros = librosRepository.findById(id).orElseThrow(null);

        return toDto(libros, LibrosDto.class);
    }
    //METODO "update" en OBSERVACION
    @Override
    public LibrosDto update(LibrosDto dto, String id, String codigoedi) {
        Libros libros = librosRepository.findById(id).orElseThrow(null);
        
        Editoriales editorial = editorialesRepository.findById(codigoedi).orElseThrow(null);
        
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
        Libros libros = librosRepository.findById(id).orElseThrow(null);
        librosRepository.delete(libros);
    }

    @Override
    public LibrosDto addAutores(String id, String codigoaut) {
        Libros libro = librosRepository.findById(id).orElseThrow(null);
        Autores autor = autoresRepository.findById(codigoaut).orElseThrow(null);                
        
        Set<Autores> autoresDeLibroActual = libro.getAutores();
        autoresDeLibroActual.add(autor);
        
        libro.setAutores(autoresDeLibroActual);
        
        Libros actualizaLibro = librosRepository.save(libro);
        
        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public LibrosDto removeAutores(String id, String codigoaut) {
        Libros libro = librosRepository.findById(id).orElseThrow(null);
        Autores autor = autoresRepository.findById(codigoaut).orElseThrow(null);                
        
        Set<Autores> autoresDeLibroActual = libro.getAutores();
        autoresDeLibroActual.remove(autor);
        
        libro.setAutores(autoresDeLibroActual);
        
        Libros actualizaLibro = librosRepository.save(libro);
        
        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public LibrosDto addGeneros(String id, String codigogen) {
        Libros libro = librosRepository.findById(id).orElseThrow(null);
        Generos genero = generosRepository.findById(codigogen).orElseThrow(null);                
        
        Set<Generos> generosDeLibroActual = libro.getGeneros();
        generosDeLibroActual.add(genero);
        
        libro.setGeneros(generosDeLibroActual);
        
        Libros actualizaLibro = librosRepository.save(libro);
        
        return toDto(actualizaLibro, LibrosDto.class);
    }

    @Override
    public LibrosDto removeGeneros(String id, String codigogen) {
        Libros libro = librosRepository.findById(id).orElseThrow(null);
        Generos genero = generosRepository.findById(codigogen).orElseThrow(null);                
        
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
    
}
